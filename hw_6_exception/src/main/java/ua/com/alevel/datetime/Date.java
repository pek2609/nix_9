package ua.com.alevel.datetime;

import ua.com.alevel.util.DateUtil;

public class Date {

    private static final int MAX_YEAR = 9999;
    private static final int MIN_YEAR = 0;
    private static final int MAX_MONTH = 12;
    private static final int MIN_MONTH = 1;
    private static final int MIN_DAY = 1;

    private int year;
    private int month;
    private int day;

    public Date() {
        this(MIN_YEAR, MIN_MONTH, MIN_DAY);
    }

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isValid() {
        return year >= MIN_YEAR && year <= MAX_YEAR
                && month >= MIN_MONTH && month <= MAX_MONTH
                && day >= MIN_DAY && day <= DateUtil.getMaxDayOfMonth(year, month);
    }

    @Override
    public String toString() {
        return "Date{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
