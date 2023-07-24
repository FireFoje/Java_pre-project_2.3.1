package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User deleteUser(Long id);

    void updateUser(User user);

    void saveUser(User user);

    public void createOrUpdate(User user);
}
