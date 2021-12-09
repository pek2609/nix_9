package ua.com.alevel;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class Parser {


    public static DateTime parse(String fullDate) {
        DateTime dateTime = new DateTime();
        String[] splitFullDate = fullDate.split(" ");
        System.out.println(splitFullDate.length);
        System.out.println(Arrays.toString(splitFullDate));
        Date date = new Date();
        Time time = new Time();
        if (splitFullDate.length < 1 || splitFullDate.length > 2) {
            throw new RuntimeException("Impossible date input");
        }
        if (splitFullDate.length == 2) {
            String stringDate = splitFullDate[0];
            String stringTime = splitFullDate[1];
            if (stringDate.contains("/")) {
                date = parseDate(stringDate, "/");
            } else if (stringDate.contains("-")) {
                date = parseDate(stringDate, "-");
            } else {
                date = new Date();
                date.setYear(parseNumber(stringDate));
            }
            time = parseTime(stringTime);
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
        checkValidDateAndTime(date, time);
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
                date.setDay(parseNumber(split[0]));
            }
            if (!split[1].isBlank()) {
                date.setMonth(parseNumber(split[1]));
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
            time = parseTime("00:00:00:000", stringTime);
        } else if (split.length == 2) {
            time = parseTime("00:00", stringTime);
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

    public static DateTime parse(String fullDate, String dateFormat) {
        DateTime dateTime = new DateTime();
        List<String> formats = Formats.getInstance().getFormats();
        String format = formats.stream()
                .filter(f -> f.equals(dateFormat))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Impossible date format"));
        String stringDateFormat = StringUtils.substringBeforeLast(dateFormat, " ");
        String stringTimeFormat = StringUtils.substringAfterLast(dateFormat, " ");
        String stringDate = StringUtils.substringBeforeLast(fullDate, " ");
        String stringTime = StringUtils.substringAfterLast(fullDate, " ");
        Date date = new Date();
        Time time = new Time();
        if (stringDateFormat.contains("/")) {
            if (stringDate.isBlank() || !stringDate.contains("/")) {
                throw new RuntimeException("Date " + stringDate + " doesn't correspond to format " + format);
            }
            date = parseDate(stringDateFormat, stringDate, "/");
        }
        if (stringDateFormat.contains("-")) {
            if (stringDateFormat.contains("mmm")) {
                stringDate = stringDate.replace(" ", "-");
            }
            if (stringDate.isBlank() || !stringDate.contains("-")) {
                throw new RuntimeException("Date " + stringDate + " doesn't correspond to format " + format);
            }
            date = parseDate(stringDateFormat, stringDate, "-");
        }
        if (StringUtils.isNotBlank(stringTimeFormat)) {
            time = parseTime(stringTimeFormat, stringTime);
        }
        checkValidDateAndTime(date, time);
        dateTime.setDate(date);
        dateTime.setTime(time);
        return dateTime;
    }

    private static void checkValidDateAndTime(Date date, Time time) {
        if (date != null && !date.isValid()) {
            throw new RuntimeException("Date is not valid");
        }
        if (time != null && !time.isValid()) {
            throw new RuntimeException("Time is not valid");
        }
    }

    private static Time parseTime(String timeFormat, String stringTime) {
        if (stringTime.lastIndexOf(":") == stringTime.length() - 1) {
            stringTime += " ";
        }
        Time time = new Time();
        String[] split = stringTime.split(":");
        if (timeFormat.equals("00:00")) {
            if (split.length != 2) {
                throw new RuntimeException("Time " + stringTime + " doesn't correspond to format " + timeFormat);
            }
            if (!split[0].isBlank()) {
                time.setHour(parseNumber(split[0]));
            }
            if (!split[1].isBlank()) {
                time.setMinute(parseNumber(split[1]));
            }
        }
        if (timeFormat.equals("00:00:00:000")) {
            if (split.length != 4) {
                throw new RuntimeException("Time " + stringTime + " doesn't correspond to format " + timeFormat);
            }
            if (!split[0].isBlank()) {
                time.setHour(parseNumber(split[0]));
            }
            if (!split[1].isBlank()) {
                time.setMinute(parseNumber(split[1]));
            }
            if (!split[2].isBlank()) {
                time.setSecond(parseNumber(split[2]));
            }
            if (!split[3].isBlank()) {
                time.setMs(parseNumber(split[3]));
            }
        }
        return time;
    }

    private static Date parseDate(String dateFormat, String stringDate, String regex) {
        Date date = new Date();
        if (stringDate.lastIndexOf(regex) == stringDate.length() - 1) {
            stringDate += " ";
        }
        String[] format = dateFormat.split(regex);
        String[] split = stringDate.split(regex);
        if (split.length == 3) {
            parseDateParam(date, split[0], format[0]);
            parseDateParam(date, split[1], format[1]);
            parseDateParam(date, split[2], format[2]);
        } else {
            throw new RuntimeException("Impossible date input");
        }
        return date;
    }

    private static void parseDateParam(Date date, String dateParam, String format) {
        switch (format) {
            case "d", "dd" -> date.setDay(parseNumber(dateParam));
            case "m", "mm" -> date.setMonth(parseNumber(dateParam));
            case "mmm" -> {
                Integer month = Formats.getInstance().getMonths().get(dateParam);
                if (month == null) {
                    throw new RuntimeException("Can't find month " + dateParam);
                }
                date.setMonth(month);
            }
            case "yyyy" -> date.setYear(parseNumber(dateParam));
            case "yy" -> {
                String substringYear = dateParam.substring(Math.max(dateParam.length() - 2, 0));
                date.setYear(parseNumber(substringYear));
            }
        }
    }
}
