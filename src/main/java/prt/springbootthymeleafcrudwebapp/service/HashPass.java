package prt.springbootthymeleafcrudwebapp.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

@Component
public class HashPass {

    public static String hashSHA512(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error initializing SHA-512 MessageDigest", e);
        }
    }

    public static boolean matchSHA512(String input, String hashedPassword) {
        String hashedInput = hashSHA512(input);
        return hashedInput.equals(hashedPassword);
    }
}