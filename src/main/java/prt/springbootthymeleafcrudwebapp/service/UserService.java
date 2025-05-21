package prt.springbootthymeleafcrudwebapp.service;

import prt.springbootthymeleafcrudwebapp.model.User;
import prt.springbootthymeleafcrudwebapp.model.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();
    void createUser(UserDTO userDTO);
    User getUserById(long id);
    void saveUser(User user);
    void deleteUserById(long id);
    boolean checkPassword(User user, String password);
    User findByEmail(String email);  // Fetch user by email
    Optional<User> getUserByEmail(String email);
    boolean authenticate(String email, String password);
}
