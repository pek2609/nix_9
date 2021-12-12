package ua.com.alevel.calendar;

import ua.com.alevel.datetime.DateTime;
import ua.com.alevel.util.DateUtil;

import java.util.concurrent.TimeUnit;

public final class Calendar {

    private DateTime dateTime;

    public Calendar(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }


    public void addHours(long hours) {
        long ms = TimeUnit.HOURS.toMillis(hours);
        long l = DateUtil.convertToMs(dateTime);
        setDateTime(DateUtil.convertToDateTime(l + ms));
    }

    public void addMinutes(long minutes) {
        long ms = TimeUnit.MINUTES.toMillis(minutes);
        long l = DateUtil.convertToMs(dateTime);
        setDateTime(DateUtil.convertToDateTime(l + ms));
    }

    public void addSeconds(long seconds) {
        long ms = TimeUnit.SECONDS.toMillis(seconds);
        long l = DateUtil.convertToMs(dateTime);
        setDateTime(DateUtil.convertToDateTime(l + ms));
    }

    public void addMs(long ms) {
        long l = DateUtil.convertToMs(dateTime);
        DateTime dateTime = DateUtil.convertToDateTime(l + ms);
        setDateTime(dateTime);
    }

    public void addDays(long days) {
        long ms = TimeUnit.DAYS.toMillis(days);
        long l = DateUtil.convertToMs(dateTime);
        setDateTime(DateUtil.convertToDateTime(l + ms));
    }

    public void addMonth(long months) {
        long newMonth = dateTime.getDate().getMonth() + months;
        while (newMonth <= 0) {
            addYear(-1);
            newMonth += 12;
        }
        while (newMonth > 12) {
            addYear(1);
            newMonth -= 12;
        }
        dateTime.getDate().setMonth((int) newMonth);
    }

    public void addYear(int years) {
        int newYear = dateTime.getDate().getYear() + years;
        if (newYear < 0 || newYear > 9999) {
            throw new RuntimeException("Date will be invalid!");
        }
        dateTime.getDate().setYear(newYear);
    }


    public void subtractHours(int hours) {
        addHours(-hours);
    }

    public void subtractMinutes(int minutes) {
        addMinutes(-minutes);
    }

    public void subtractSeconds(int seconds) {
        addSeconds(-seconds);
    }

    public void subtractMs(int ms) {
        addMs(-ms);
    }

    public void subtractDays(int days) {
        addDays(-days);
    }

    public void subtractMonth(int months) {
        addMonth(-months);
    }

    public void subtractYear(int years) {
        addYear(-years);
    }

    public long differenceInMs(DateTime dateTime) {
        return DateUtil.convertToMs(this.dateTime) - DateUtil.convertToMs(dateTime);
    }

    public long differenceInSeconds(DateTime dateTime) {
        return TimeUnit.MILLISECONDS.toSeconds(differenceInMs(dateTime));
    }

    public long differenceInMinutes(DateTime dateTime) {
        return TimeUnit.MILLISECONDS.toMinutes(differenceInMs(dateTime));
    }

    public long differenceInHours(DateTime dateTime) {
        return TimeUnit.MILLISECONDS.toHours(differenceInMs(dateTime));
    }

    public long differenceInDays(DateTime dateTime) {
        return TimeUnit.MILLISECONDS.toDays(differenceInMs(dateTime));
    }

    public long differenceInMonths(DateTime dateTime) {
        return differenceInMs(dateTime) / 2_629_746_000L;
    }

    public long differenceInYears(DateTime dateTime) {
        return differenceInMs(dateTime) / 31_556_952_000L;
    }


}
