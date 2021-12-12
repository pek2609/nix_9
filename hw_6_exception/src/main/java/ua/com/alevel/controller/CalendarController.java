package ua.com.alevel.controller;

import ua.com.alevel.calendar.Calendar;
import ua.com.alevel.dateformats.Formats;
import ua.com.alevel.datetime.DateTime;
import ua.com.alevel.util.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CalendarController {


    private static final String MENU_ADD = """
            -------------Menu-------------
            1 - add ms
            2 - add seconds
            3 - add minutes
            4 - add hours
            5 - add days
            6 - add months
            7 - add years
            0 - Back
            ------------------------------""";

    private static final String MENU_SUBTRACT = """
            -------------Menu-------------
            1 - subtract ms
            2 - subtract seconds
            3 - subtract minutes
            4 - subtract hours
            5 - subtract days
            6 - subtract months
            7 - subtract years
            0 - Back
            ------------------------------""";


    private static final String MENU_DIFFERENCE = """
            -------------Menu-------------
            1 - difference ms
            2 - difference seconds
            3 - difference minutes
            4 - difference hours
            5 - difference days
            6 - difference months
            7 - difference years
            0 - Back
            ------------------------------""";

    private static final String MENU = """
            -------------Menu-------------
            1 - add
            2 - subtract
            3 - difference
            4 - sort
            5 - show date
            0 - Exit
            ------------------------------""";


    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Pls, input your date, you can input with format, or without\nAvailable formats:\n--------------");
        Formats.getInstance().getFormats().forEach(System.out::println);
        try {
            DateTime dateTime = inputDateTime(reader);
            Calendar calendar = new Calendar(dateTime);
            chooseOption(reader, calendar);
        } catch (NumberFormatException e) {
            System.out.println("problem = " + e.getMessage());
        }
    }

    private void chooseOption(BufferedReader reader, Calendar calendar) {
        while (true) {
            try {
                System.out.println(MENU);
                System.out.println("Enter your option:");
                int op = Integer.parseInt(reader.readLine());
                switch (op) {
                    case 1 -> add(reader, calendar);
                    case 2 -> subtract(reader, calendar);
                    case 3 -> difference(reader, calendar);
                    case 4 -> sort(reader);
                    case 5 -> System.out.println(outputDateTime(calendar.getDateTime(), reader));
                    case 0 -> System.exit(0);
                    default -> System.out.println("Unexpected Option");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("problem = " + e.getMessage());
            }
        }
    }

    private void add(BufferedReader reader, Calendar calendar) {
        while (true) {
            System.out.println(MENU_ADD);
            System.out.println("Enter your option:");
            try {
                int op = Integer.parseInt(reader.readLine());
                switch (op) {
                    case 1 -> calendar.addMs(inputNumber(reader));
                    case 2 -> calendar.addSeconds(inputNumber(reader));
                    case 3 -> calendar.addMinutes(inputNumber(reader));
                    case 4 -> calendar.addHours(inputNumber(reader));
                    case 5 -> calendar.addDays(inputNumber(reader));
                    case 6 -> calendar.addMonth(inputNumber(reader));
                    case 7 -> calendar.addYear(inputNumber(reader));
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Unexpected Option");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("problem = " + e.getMessage());
            }
        }

    }

    private void subtract(BufferedReader reader, Calendar calendar) {
        while (true) {
            System.out.println(MENU_SUBTRACT);
            System.out.println("Enter your option:");
            try {
                int op = Integer.parseInt(reader.readLine());
                switch (op) {
                    case 1 -> calendar.subtractMs(inputNumber(reader));
                    case 2 -> calendar.subtractSeconds(inputNumber(reader));
                    case 3 -> calendar.subtractMinutes(inputNumber(reader));
                    case 4 -> calendar.subtractHours(inputNumber(reader));
                    case 5 -> calendar.subtractDays(inputNumber(reader));
                    case 6 -> calendar.subtractMonth(inputNumber(reader));
                    case 7 -> calendar.subtractYear(inputNumber(reader));
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Unexpected Option");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("problem = " + e.getMessage());
            }
        }
    }

    private void difference(BufferedReader reader, Calendar calendar) {
        while (true) {
            System.out.println(MENU_DIFFERENCE);
            System.out.println("Enter your option:");
            try {
                int op = Integer.parseInt(reader.readLine());
                switch (op) {
                    case 1 -> {
                        DateTime dateTime = inputDateTime(reader);
                        long res = calendar.differenceInMs(dateTime);
                        System.out.println("res = " + res);
                    }
                    case 2 -> {
                        DateTime dateTime = inputDateTime(reader);
                        long res = calendar.differenceInSeconds(dateTime);
                        System.out.println("res = " + res);
                    }
                    case 3 -> {
                        DateTime dateTime = inputDateTime(reader);
                        long res = calendar.differenceInMinutes(dateTime);
                        System.out.println("res = " + res);
                    }
                    case 4 -> {
                        DateTime dateTime = inputDateTime(reader);
                        long res = calendar.differenceInHours(dateTime);
                        System.out.println("res = " + res);
                    }
                    case 5 -> {
                        DateTime dateTime = inputDateTime(reader);
                        long res = calendar.differenceInDays(dateTime);
                        System.out.println("res = " + res);
                    }
                    case 6 -> {
                        DateTime dateTime = inputDateTime(reader);
                        long res = calendar.differenceInMonths(dateTime);
                        System.out.println("res = " + res);
                    }
                    case 7 -> {
                        DateTime dateTime = inputDateTime(reader);
                        long res = calendar.differenceInYears(dateTime);
                        System.out.println("res = " + res);
                    }
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Unexpected Option");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("problem = " + e.getMessage());
            }
        }
    }

    private void sort(BufferedReader reader) {
        List<DateTime> list = new ArrayList<>();
        System.out.println("Please input number of dates to be input");
        try {
            int n = inputNumber(reader);
            for (int i = 0; i < n; i++) {
                System.out.println("Please input " + (i + 1) + " date:");
                DateTime dateTime = inputDateTime(reader);
                list.add(dateTime);
            }
            System.out.println("List of dates:");
            list.forEach(x-> System.out.println(Parser.parse(x)));
            System.out.println("-------------\nDo you want to sort asc or desc(asc - A, desc - D)?");
            System.out.println("Please, enter (A or D)");
            String res = reader.readLine();
            switch (res) {
                case "A" -> list.sort(Comparator.naturalOrder());
                case "D" -> list.sort(Comparator.reverseOrder());
                default -> System.out.println("Unexpected option...");
            }
            System.out.println("List of dates:");
            list.forEach(x-> System.out.println(Parser.parse(x)));
        } catch (IOException | NumberFormatException e) {
            System.out.println("problem = " + e.getMessage());
        }
    }

    private String outputDateTime(DateTime dateTime, BufferedReader reader) {
        System.out.println("Available formats:\n--------------");
        Formats.getInstance().getFormats().forEach(System.out::println);
        while (true) {
            System.out.println("-------------\nDo you want to output date with certain format?");
            System.out.println("Please, enter (Y/N)");
            try {
                String res = reader.readLine();
                switch (res) {
                    case "Y" -> {
                        System.out.println("-------------\nYour format:");
                        String format = reader.readLine();
                        return Parser.parse(dateTime, format);
                    }
                    case "N" -> {
                        return Parser.parse(dateTime);
                    }
                    default -> System.out.println("Unexpected option...");
                }
            } catch (RuntimeException | IOException e) {
                System.out.println("problem = " + e.getMessage());
            }
        }
    }

    private int inputNumber(BufferedReader reader) throws IOException {
        System.out.println("Please, enter number:");
        return Integer.parseInt(reader.readLine());
    }

    private DateTime inputDateTime(BufferedReader reader) {
        DateTime dateTime;
        while (true) {
            System.out.println("-------------\nDo you want to input date with certain format?");
            System.out.println("Please, enter (Y/N)");
            try {
                String res = reader.readLine();
                switch (res) {
                    case "Y" -> {
                        System.out.println("-------------\nYour format:");
                        String format = reader.readLine();
                        System.out.println("-------------\nYour date:");
                        String stringDateTime = reader.readLine();
                        dateTime = Parser.parse(stringDateTime, format);
                        return dateTime;
                    }
                    case "N" -> {
                        System.out.println("-------------\nYour date:");
                        String stringDateTime = reader.readLine();
                        dateTime = Parser.parse(stringDateTime);
                        return dateTime;
                    }
                    default -> System.out.println("Unexpected option...");
                }
            } catch (RuntimeException | IOException e) {
                System.out.println("problem = " + e.getMessage());
            }
        }
    }
}
