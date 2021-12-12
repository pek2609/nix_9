package ua.com.alevel;

import org.junit.jupiter.api.BeforeAll;
import ua.com.alevel.calendar.Calendar;
import ua.com.alevel.datetime.DateTime;
import ua.com.alevel.util.Parser;

class CalendarTest {

    static DateTime dateTime = new DateTime();
    static Calendar calendar;

    @BeforeAll
    static void init(){
        dateTime = Parser.parse("28-12-734 17:45");
        System.out.println(dateTime);
        calendar = new Calendar(dateTime);
    }

    @org.junit.jupiter.api.Test
    void addHours() {
        calendar.addMonth(170);
        System.out.println(dateTime);
    }

    @org.junit.jupiter.api.Test
    void addMinutes() {
    }

    @org.junit.jupiter.api.Test
    void addSeconds() {
    }

    @org.junit.jupiter.api.Test
    void addMs() {
    }

    @org.junit.jupiter.api.Test
    void addDays() {
    }

    @org.junit.jupiter.api.Test
    void addMonth() {
    }
}