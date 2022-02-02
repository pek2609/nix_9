package ua.com.alevel.exception;

public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException(String message) {
        super(message);
    }
}
