package sn.isi.service;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.dao.IUserRepository;
import sn.isi.dto.User;
import sn.isi.exception.EntityNotFoundException;
import sn.isi.exception.RequestException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import sn.isi.mapping.UserMapper;

@Service
public class UserServiceImpl implements IUserService {
    private IUserRepository iUserRepository;
    private UserMapper userMapper;
    MessageSource messageSource;

    public UserServiceImpl(IUserRepository iUserRepository, UserMapper userMapper, MessageSource messageSource) {
        this.iUserRepository = iUserRepository;
        this.userMapper = userMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<User>  getUsers() {
        return StreamSupport.stream(iUserRepository.findAll().spliterator(), false)
                .map(userMapper::toUser)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public User getUser(int id) {
        return userMapper.toUser(iUserRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Transactional
    public User createUser(User user) {
        iUserRepository.findByEmail(user.getEmail())
                .ifPresent(entity -> {
                    throw new RequestException(messageSource.getMessage("user.exists", new Object[]{user.getEmail()},
                            Locale.getDefault()), HttpStatus.CONFLICT);
                });
        return userMapper.toUser(iUserRepository.save(userMapper.fromUser(user)));
    }

    @Transactional
    public User updateUser(int id, User user) {
        return iUserRepository.findById(id)
                .map(entity -> {
                    user.setId(id);
                    return userMapper.toUser(
                            iUserRepository.save(userMapper.fromUser(user)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteUser(int id) {
        try {
            iUserRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("user.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
