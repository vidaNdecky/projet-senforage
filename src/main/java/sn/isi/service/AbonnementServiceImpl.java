package sn.isi.service;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.dao.IAbonnementRepository;
import sn.isi.dto.Abonnement;
import sn.isi.exception.EntityNotFoundException;
import sn.isi.exception.RequestException;
import sn.isi.mapping.AbonnementMapper;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@AllArgsConstructor
@Service
public class AbonnementServiceImpl implements IAbonnementService {
    private IAbonnementRepository iAbonnementRepository;
    private AbonnementMapper abonnementMapper;
    MessageSource messageSource;

    @Transactional(readOnly = true)
    public List<Abonnement>  getAbonnements() {
        return StreamSupport.stream(iAbonnementRepository.findAll().spliterator(), false)
                .map(abonnementMapper::toAbonnement)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Abonnement getAbonnement(int id) {
        return abonnementMapper.toAbonnement(iAbonnementRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("abonnement.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Transactional
    public Abonnement createAbonnement(Abonnement abonnement) {
        iAbonnementRepository.findById(abonnement.getNumeroAbonnement())
                .ifPresent(entity -> {
                    throw new RequestException(messageSource.getMessage("abonnement.exists", new Object[]{abonnement.getNumeroAbonnement()},
                            Locale.getDefault()), HttpStatus.CONFLICT);
                });
        return abonnementMapper.toAbonnement(iAbonnementRepository.save(abonnementMapper.fromAbonnement(abonnement)));
    }

    @Transactional
    public Abonnement updateAbonnement(int id, Abonnement abonnement) {
        return iAbonnementRepository.findById(id)
                .map(entity -> {
                    abonnement.setNumeroAbonnement(id);
                    return abonnementMapper.toAbonnement(
                            iAbonnementRepository.save(abonnementMapper.fromAbonnement(abonnement)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("abonnement.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteAbonnement(int id) {
        try {
            iAbonnementRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("abonnement.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
