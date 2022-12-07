package sn.isi.service;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.dao.IFactureRepository;
import sn.isi.dto.Facture;
import sn.isi.exception.EntityNotFoundException;
import sn.isi.exception.RequestException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import sn.isi.mapping.FactureMapper;

@Service
public class FactureServiceImpl implements IFactureService {
    private IFactureRepository iFactureRepository;
    private FactureMapper factureMapper;
    MessageSource messageSource;

    public FactureServiceImpl(IFactureRepository iFactureRepository, FactureMapper factureMapper, MessageSource messageSource) {
        this.iFactureRepository = iFactureRepository;
        this.factureMapper = factureMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<Facture>  getFactures() {
        return StreamSupport.stream(iFactureRepository.findAll().spliterator(), false)
                .map(factureMapper::toFacture)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Facture getFacture(int id) {
        return factureMapper.toFacture(iFactureRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("facture.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Transactional
    public Facture createFacture(Facture facture) {
        iFactureRepository.findById(facture.getNumFacture())
                .ifPresent(entity -> {
                    throw new RequestException(messageSource.getMessage("facture.exists", new Object[]{facture.getNumFacture()},
                            Locale.getDefault()), HttpStatus.CONFLICT);
                });
        return factureMapper.toFacture(iFactureRepository.save(factureMapper.fromFacture(facture)));
    }

    @Transactional
    public Facture updateFacture(int id, Facture facture) {
        return iFactureRepository.findById(id)
                .map(entity -> {
                    facture.setNumFacture(id);
                    return factureMapper.toFacture(
                            iFactureRepository.save(factureMapper.fromFacture(facture)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("facture.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteFacture(int id) {
        try {
            iFactureRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("facture.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
