package ua.com.alevel.tasks.dateparser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateValidator {

    private DateValidator() {
    }

    public static boolean isValid(String dateFormat, String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDate.parse(date, dateTimeFormatter);

        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }
}
