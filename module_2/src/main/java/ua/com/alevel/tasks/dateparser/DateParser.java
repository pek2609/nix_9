package ua.com.alevel.tasks.dateparser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateParser {

    private static final String RESULT_FORMAT = "yyyyMMdd";

    private final List<String> inputDates;
    private final List<String> dateFormats;

    public DateParser(List<String> inputDates) {
        this.inputDates = inputDates;
        dateFormats = new ArrayList<>();
        dateFormats.add("yyyy/MM/dd");
        dateFormats.add("dd/MM/yyyy");
        dateFormats.add("MM-dd-yyyy");
    }

    public List<String> parse() {
        List<LocalDate> filterDates = filterDates();
        List<String> result = new ArrayList<>();
        for (LocalDate filterDate : filterDates) {
            result.add(filterDate.format(DateTimeFormatter.ofPattern(RESULT_FORMAT)));
        }
        return result;
    }

    private List<LocalDate> filterDates() {
        List<LocalDate> localDates = new ArrayList<>();
        for (String inputDate : inputDates) {
            for (String dateFormat : dateFormats) {
                if (DateValidator.isValid(dateFormat, inputDate)) {
                    localDates.add(LocalDate.parse(inputDate, DateTimeFormatter.ofPattern(dateFormat)));
                }
            }
        }
        return localDates;
    }
}
