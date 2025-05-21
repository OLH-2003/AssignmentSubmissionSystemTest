package prt.springbootthymeleafcrudwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prt.springbootthymeleafcrudwebapp.repository.UserRepository;
import prt.springbootthymeleafcrudwebapp.model.User;
import prt.springbootthymeleafcrudwebapp.service.HashPass;
import prt.springbootthymeleafcrudwebapp.exception.UsernameNotFound;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:63342", allowCredentials = "true")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        String inputEmail = loginRequest.getEmail();
        String inputPassword = loginRequest.getPasswordHash();

        User user;
        try {
            user = userRepository.findByEmail(inputEmail)
                    .orElseThrow(() -> new UsernameNotFound("User not found"));
        } catch (UsernameNotFound e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email address");
        }


        if (HashPass.matchSHA512(inputPassword, user.getPasswordHash())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}