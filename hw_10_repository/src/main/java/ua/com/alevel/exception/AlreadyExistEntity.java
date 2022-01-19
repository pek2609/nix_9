package ua.com.alevel.exception;

public class AlreadyExistEntity extends RuntimeException {

    public AlreadyExistEntity(String message) {
        super(message);
    }
}
