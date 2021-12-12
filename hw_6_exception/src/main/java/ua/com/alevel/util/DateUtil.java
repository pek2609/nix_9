package ua.com.alevel.util;

import ua.com.alevel.datetime.Date;
import ua.com.alevel.datetime.DateTime;
import ua.com.alevel.datetime.Time;

import java.util.concurrent.TimeUnit;

public final class DateUtil {

    private DateUtil() {
    }

    public static boolean isLeap(int year) {
        return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
    }

    public static int getMaxDayOfMonth(int year, int month) {
        int[] daysInEachMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month == 2 && isLeap(year)) {
            ++daysInEachMonth[1];
        }
        return daysInEachMonth[month - 1];
    }

    public static long convertToMs(DateTime dateTime) {
        int year = dateTime.getDate().getYear();
        int month = dateTime.getDate().getMonth();
        int day = dateTime.getDate().getDay();
        int hour = dateTime.getTime().getHour();
        int minute = dateTime.getTime().getMinute();
        int second = dateTime.getTime().getSecond();
        int ms = dateTime.getTime().getMs();
        long res = 0L;
        long numOfLeapYears = (year - 1 + 4) / 4 - (year - 1 + 100) / 100 + (year - 1 + 400) / 400;
        long numOfNoLeapYears = year - numOfLeapYears;
        res += numOfLeapYears * 366 * 24 * 3600 * 1000 + numOfNoLeapYears * 365 * 24 * 3600 * 1000;
        for (int i = 1; i < month; i++) {
            res += (long) getMaxDayOfMonth(year, i) * 24 * 3600 * 1000;
        }
        res += (long) (day - 1) * 24 * 3600 * 1000;
        res += (long) hour * 3600 * 1000;
        res += (long) minute * 60 * 1000;
        res += second * 1000L;
        res += ms;
        return res;
    }

    public static DateTime convertToDateTime(long res) {
        if (res < 0) {
            throw new RuntimeException("Argument must be > 0");
        }
        int ms = (int) (res % 1000);
        int second = (int) (TimeUnit.MILLISECONDS.toSeconds(res) % 60);
        int minute = (int) TimeUnit.MILLISECONDS.toMinutes(res) % 60;
        int hour = (int) TimeUnit.MILLISECONDS.toHours(res) % 24;
        int months = (int) (res / 2_629_746_000L) % 12 + 1;
        int year = (int) (res / 31_556_952_000L);
        int days = (int) (TimeUnit.MILLISECONDS.toDays(res));
        for (int i = 0; i < year; i++) {
            if (isLeap(i)) {
                days -= 366;
            } else {
                days -= 365;
            }
        }
        for (int i = 1; i < months; i++) {
            days -= (long) getMaxDayOfMonth(year, i);
        }
        DateTime dateTime = new DateTime();
        Date date = new Date();
        date.setYear(year);
        date.setMonth(months);
        date.setDay(days + 1);
        Time time = new Time();
        time.setHour(hour);
        time.setMinute(minute);
        time.setSecond(second);
        time.setMs(ms);
        if (!date.isValid()) {
            throw new RuntimeException("Date is not valid");
        }
        if (!time.isValid()) {
            throw new RuntimeException("Time is not valid");
        }
        dateTime.setDate(date);
        dateTime.setTime(time);
        return dateTime;
    }
}
