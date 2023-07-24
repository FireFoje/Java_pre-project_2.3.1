package web.repository;

import web.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    User getUserById(Long id);

    void updateUser(User user);

    void saveUser(User user);

    User deleteUser(Long id);
}
