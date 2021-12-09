package ua.com.alevel;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class ExceptionMain {

    public static void main(String[] args) {
        DateTime parse = Parser.parse("09 Апрель 789 23:45","dd-mmm-yyyy 00:00");
        System.out.println(parse);
    }
}
