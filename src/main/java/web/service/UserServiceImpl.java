package web.service;

import org.springframework.stereotype.Service;
import web.model.User;
import web.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public void createOrUpdate(User user){
        if (user.getId() == 0) {
            saveUser(user);
        } else {
            updateUser(user);
        }
    }

    @Override
    public User deleteUser(Long id) {
        return userRepository.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public void saveUser(User user) {
        userRepository.saveUser(user);
    }
}
