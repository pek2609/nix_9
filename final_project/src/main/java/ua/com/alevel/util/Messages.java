package ua.com.alevel.util;

public final class Messages {

    private Messages() {
    }

    public static final String NOT_NULL = "must not be empty";
    public static final String INVALID_NUMBER = "impossible! examples: +380661234567, 0661234567";
    public static final String INVALID_IMAGE_URL = "impossible image url";
    public static final String INVALID_EMAIL = "impossible! examples: user@gmail.com";
    public static final String INVALID_TYPE = "please choose one of the options";
    public static final String INVALID_PERCENT = "input percent(1-99)";

    public static String entityLog(String operation, String entityName, Long id, String status) {
        return operation + " " + entityName + " " + status + ", id = " + id;
    }

    public static String entityLog(String operation, String entityName, String status) {
        return operation + " " + entityName + " " + status;
    }

    public static String entityLog(String operation, String entityName) {
        return operation + " " + entityName;
    }

    public static String entityLog(String operation, String entityName, Long id) {
        return operation + " " + entityName + ", id = " + id;
    }

    public static String entityNotFoundLog(String entityName, Long id) {
        return entityName + " is not found, id = " + id;
    }
}

