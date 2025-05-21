package prt.springbootthymeleafcrudwebapp.exception;

/**
 * Custom exception thrown when a user is not found in the database.
 */
public class UsernameNotFound extends RuntimeException {

    public UsernameNotFound() {
        super("Username not found");
    }

    public UsernameNotFound(String message) {
        super(message);
    }

    public UsernameNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
