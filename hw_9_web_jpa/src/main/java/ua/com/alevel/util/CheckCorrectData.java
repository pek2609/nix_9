package ua.com.alevel.util;

public final class CheckCorrectData {

    private CheckCorrectData() {
    }

    public static boolean isBlankOrNullString(String data) {
        return data == null || data.isBlank();
    }

    public static boolean isCorrectNumber(Number number, Number from, Number to) {
        return number.doubleValue() >= from.doubleValue() && number.doubleValue() <= to.doubleValue();
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        return !phoneNumber.matches("^\\+?3?8?(0\\d{9})$");
    }

    public static boolean checkEmail(String email) {
        return !email.matches("^[\\w.+\\-]+@gmail\\.com$");
    }
}
