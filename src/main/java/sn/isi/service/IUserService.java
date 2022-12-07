package sn.isi.service;

import sn.isi.dto.User;

import java.util.List;

public interface IUserService {
    public User createUser(User user);
    public User updateUser(int id, User user);
    public void deleteUser(int id);
    public User getUser(int id);
    public List<User> getUsers();
}
