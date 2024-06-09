package ua.com.alevel.exception;

public class CustomBadRequestException extends RuntimeException {

    public CustomBadRequestException(String message) {
        super(message);
    }
}
