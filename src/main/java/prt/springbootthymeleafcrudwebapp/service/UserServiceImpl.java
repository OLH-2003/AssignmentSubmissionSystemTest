package prt.springbootthymeleafcrudwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prt.springbootthymeleafcrudwebapp.exception.UsernameNotFound;
import prt.springbootthymeleafcrudwebapp.model.Staff;
import prt.springbootthymeleafcrudwebapp.model.Student;
import prt.springbootthymeleafcrudwebapp.model.User;
import prt.springbootthymeleafcrudwebapp.model.UserDTO;
import prt.springbootthymeleafcrudwebapp.repository.StaffRepository;
import prt.springbootthymeleafcrudwebapp.repository.StudentRepository;
import prt.springbootthymeleafcrudwebapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, StudentRepository studentRepository, StaffRepository staffRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found for id: " + id));
    }

    @Override
    public void createUser(UserDTO userDTO) {
        String hashedPassword = HashPass.hashSHA512(userDTO.getPassword());

        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setPasswordHash(hashedPassword);
        userRepository.save(user);

        if ("student".equalsIgnoreCase(userDTO.getRole())) {
            Student student = new Student();
            student.setUserId(user.getId());
            student.setStudentId(userDTO.getMatriculationNumber());
            studentRepository.save(student);
        } else if ("staff".equalsIgnoreCase(userDTO.getRole())) {
            Staff staff = new Staff();
            staff.setUserId(user.getId());
            staff.setStaffNumber(userDTO.getStaffNumber());
            staffRepository.save(staff);
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFound("User not found"));
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        String hashedPassword = HashPass.hashSHA512(password);
        return user.getPasswordHash().equals(hashedPassword);
    }

    @Override
    public boolean authenticate(String email, String password) {
        User user = findByEmail(email);

        String hashedPassword = HashPass.hashSHA512(password);
        return hashedPassword.equals(user.getPasswordHash());
    }
}
