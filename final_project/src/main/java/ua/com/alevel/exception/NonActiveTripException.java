package ua.com.alevel.exception;

public class NonActiveTripException extends RuntimeException {

    public NonActiveTripException(String message) {
        super(message);
    }
}
