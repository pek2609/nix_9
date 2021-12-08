package ua.com.alevel;

import java.util.Arrays;

public class Parser {


    public static DateTime parse(String fullDate) {
        DateTime dateTime = new DateTime();
        String[] splitFullDate = fullDate.split(" ");
        System.out.println(splitFullDate.length);
        System.out.println(Arrays.toString(splitFullDate));
        Time time = null;
        Date date = null;
        if (splitFullDate.length < 1 || splitFullDate.length > 2) {
            throw new RuntimeException("Impossible date input");
        }
        if (splitFullDate.length == 2) {
            String stringDate = splitFullDate[0];
            String strigTime = splitFullDate[1];
            if (stringDate.contains("/")) {
                date = parseDate(stringDate, "/");
            } else if (stringDate.contains("-")) {
                date = parseDate(stringDate, "-");
            } else {
                date = new Date();
                date.setYear(parseNumber(stringDate));
            }
            time = parseTime(strigTime);
        } else {
            if (splitFullDate[0].contains(":")) {
                time = parseTime(splitFullDate[0]);
            } else if (splitFullDate[0].contains("/")) {
                date = parseDate(splitFullDate[0], "/");
            } else if (splitFullDate[0].contains("-")) {
                date = parseDate(splitFullDate[0], "-");
            } else {
                date = new Date();
                date.setYear(parseNumber(splitFullDate[0]));
            }
        }
        if (date != null && !date.isValid()) {
            throw new RuntimeException("Date is not valid");
        }
        if (time != null && !time.isValid()) {
            throw new RuntimeException("Time is not valid");
        }
        dateTime.setDate(date);
        dateTime.setTime(time);
        return dateTime;
    }

    private static Date parseDate(String stringDate, String regex) {
        Date date = new Date();
        if (stringDate.lastIndexOf(regex) == stringDate.length() - 1) {
            stringDate += " ";
        }
        String[] split = stringDate.split(regex);
        if (split.length == 3) {
            if (!split[0].isBlank()) {
                date.setYear(parseNumber(split[0]));
            }
            if (!split[1].isBlank()) {
                date.setYear(parseNumber(split[1]));
            }
            if (!split[2].isBlank()) {
                date.setYear(parseNumber(split[2]));
            }
        } else {
            throw new RuntimeException("Impossible date input");
        }
        return date;
    }

    private static Time parseTime(String stringTime) {
        if (stringTime.lastIndexOf(":") == stringTime.length() - 1) {
            stringTime += " ";
        }
        Time time = new Time();
        String[] split = stringTime.split(":");
        if (split.length == 4) {
            if (!split[0].isBlank()) {
                time.setHour(parseNumber(split[0]));
            }
            if (!split[1].isBlank()) {
                time.setMinute(parseNumber(split[1]));
            }
            if (!split[3].isBlank()) {
                time.setSecond(parseNumber(split[2]));
            }
            if (!split[2].isBlank()) {
                time.setMs(parseNumber(split[3]));
            }
        } else if (split.length == 2) {
            if (!split[0].isBlank()) {
                time.setHour(parseNumber(split[0]));
            }
            if (!split[1].isBlank()) {
                time.setMinute(parseNumber(split[1]));
            }
        } else {
            throw new RuntimeException("Impossible time input");
        }
        return time;
    }

    private static int parseNumber(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException nfe) {
            throw new RuntimeException("Cannot convert " + string + " to number");
        }
    }

    public static DateTime parse(String date, String dateFormat) {
        return null;
    }
}
