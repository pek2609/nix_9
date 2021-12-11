package ua.com.alevel;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Parser {

    public static DateTime parse(String fullDate) {
        DateTime dateTime = null;
        for (String format : Formats.getInstance().getFormats()) {
            try {
                dateTime = parse(fullDate, format);
            } catch (Exception ignored) {
            }
        }
        if (dateTime == null) {
            throw new RuntimeException("Impossible date input");
        }
        return dateTime;
    }

    public static DateTime parse(String fullDate, String dateFormat) {
        DateTime dateTime = new DateTime();
        List<String> formats = Formats.getInstance().getFormats();
        if (!formats.contains(dateFormat)) {
            throw new RuntimeException("Impossible date format");
        }
        if (StringUtils.countMatches(fullDate, " ") == 2) {
            fullDate = fullDate.replace(" ", "-");
        }
        String stringDateFormat = StringUtils.substringBeforeLast(dateFormat, " ");
        String stringTimeFormat = StringUtils.substringAfterLast(dateFormat, " ");
        String stringDate = StringUtils.substringBeforeLast(fullDate, " ");
        String stringTime = StringUtils.substringAfterLast(fullDate, " ");
        Date date = new Date();
        Time time = new Time();
        if (stringDateFormat.contains("/")) {
            if (stringDate.isBlank() || !stringDate.contains("/")) {
                throw new RuntimeException("Date " + stringDate + " doesn't correspond to format " + dateFormat);
            }
            date = parseDate(stringDateFormat, stringDate, "/");
        }
        if (stringDateFormat.contains("-")) {
            if (stringDateFormat.contains("mmm")) {
                stringDate = stringDate.replace(" ", "-");
            }
            if (stringDate.isBlank() || !stringDate.contains("-")) {
                throw new RuntimeException("Date " + stringDate + " doesn't correspond to format " + dateFormat);
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

    private static int parseNumber(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException nfe) {
            throw new RuntimeException("Cannot convert " + string + " to number");
        }
    }

    private static Date parseDate(String dateFormat, String stringDate, String regex) {
        Date date = new Date();
        if (stringDate.lastIndexOf(regex) == stringDate.length() - 1) {
            stringDate += " ";
        }
        String[] format = dateFormat.split(regex);
        String[] split = stringDate.split(regex);
        if (split.length == 3) {
            for (int i = 0; i < 3; i++) {
                if (StringUtils.isNotBlank(split[i])) {
                    parseDateParam(date, split[i], format[i]);
                }
            }
        } else {
            throw new RuntimeException("Impossible date input");
        }
        return date;
    }

    private static void parseDateParam(Date date, String dateParam, String format) {
        switch (format) {
            case "d", "dd" -> {
                int day = parseNumber(dateParam);
                checkNumberOfDigitsEqualToFormat(format, dateParam, day);
                date.setDay(day);
            }
            case "m", "mm" -> {
                int month = parseNumber(dateParam);
                checkNumberOfDigitsEqualToFormat(format, dateParam, month);
                date.setMonth(month);
            }
            case "mmm" -> {
                Integer month = Formats.getInstance().getMonths().get(dateParam);
                if (month == null) {
                    throw new RuntimeException("Can't find month " + dateParam);
                }
                date.setMonth(month);
            }
            case "yyyy" -> date.setYear(parseNumber(dateParam));
            case "yy" -> {
                int year = parseNumber(dateParam);
                checkNumberOfDigitsEqualToFormat(format, dateParam, year);
                date.setYear(year);
            }
        }
    }

    private static void checkNumberOfDigitsEqualToFormat(String format, String dateParam, int dateParamNum) {
        if (format.length() != dateParam.length() && !(format.length() == 1 && dateParamNum > 9)) {
                throw new RuntimeException("Date parameter " + dateParam + " doesn't correspond to " + format);
        }
    }

    public static String parse(DateTime dateTime) {
        return parse(dateTime,"dd/mm/yyyy 00:00:00:000");
    }


    public static String parse(DateTime dateTime, String dateFormat) {
        StringBuilder dateTimeString = new StringBuilder();
        List<String> formats = Formats.getInstance().getFormats();
        if (!formats.contains(dateFormat)) {
            throw new RuntimeException("Impossible date format");
        }
        String stringDateFormat = StringUtils.substringBeforeLast(dateFormat, " ");
        String stringTimeFormat = StringUtils.substringAfterLast(dateFormat, " ");
        Date date = dateTime.getDate();
        Time time = dateTime.getTime();
        if (stringDateFormat.contains("/")) {
            dateTimeString.append(parseDate(date, stringDateFormat, "/"));
        }
        if (stringDateFormat.contains("-")) {
            dateTimeString.append(parseDate(date, stringDateFormat, "-"));
        }
        if (StringUtils.isNotBlank(stringTimeFormat)) {
            dateTimeString.append(" ").append(parseTime(time, stringTimeFormat));
        }
        return dateTimeString.toString();
    }

    private static String parseTime(Time time, String timeFormat) {
        StringBuilder timeString = new StringBuilder();
        if (timeFormat.equals("00:00")) {
            timeString.append(addZerosIfNeeded(time.getHour(),2))
                    .append(":").append(addZerosIfNeeded(time.getMinute(),2));
        }
        if (timeFormat.equals("00:00:00:000")) {
            timeString.append(addZerosIfNeeded(time.getHour(),2)).append(":")
                    .append(addZerosIfNeeded(time.getMinute(),2)).append(":")
                    .append(addZerosIfNeeded(time.getSecond(),2)).append(":")
                    .append(addZerosIfNeeded(time.getMs(),3));
        }
        return timeString.toString();
    }

    private static String addZerosIfNeeded(int param, int neededLength) {
        return "0".repeat(Math.max(0, neededLength - String.valueOf(param).length())) +
                param;
    }

    private static String parseDate(Date date, String dateFormat, String regex) {
        StringBuilder dateString = new StringBuilder();
        String[] format = dateFormat.split(regex);
        if (dateFormat.contains("mmm")) {
            regex = " ";
        }
        for (int i = 0; i < 3; i++) {
            if (i == 2) {
                dateString.append(parseDateParam(date, format[i]));
            } else {
                dateString.append(parseDateParam(date, format[i])).append(regex);
            }
        }
        return dateString.toString();
    }

    private static String parseDateParam(Date date, String format) {
        StringBuilder dateParam = new StringBuilder();
        switch (format) {
            case "d" -> dateParam.append(date.getDay());
            case "dd" -> dateParam.append(addZerosIfNeeded(date.getDay(),2));
            case "m" -> dateParam.append(date.getMonth());
            case "mm" -> dateParam.append(addZerosIfNeeded(date.getMonth(),2));
            case "mmm" -> dateParam.append(Formats.getInstance().getMonths().entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().equals(date.getMonth()))
                    .findFirst().get().getKey());
            case "yyyy" -> dateParam.append(addZerosIfNeeded(date.getYear(),4));
            case "yy" -> {
                String dateString = addZerosIfNeeded(date.getYear(), 2);
                dateParam.append(dateString.substring(Math.max(dateString.length() - 2, 0)));
            }
        }
        return dateParam.toString();
    }
}


