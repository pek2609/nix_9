package ua.com.alevel.datetime;

import ua.com.alevel.util.DateUtil;

public class DateTime implements Comparable<DateTime> {

    private Date date;
    private Time time;

    public DateTime() {
        date = new Date();
        time = new Time();
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "DateTime{" +
                "date=" + date +
                ", time=" + time +
                '}';
    }

    @Override
    public int compareTo(DateTime o) {
        long l = DateUtil.convertToMs(this) - DateUtil.convertToMs(o);
        if (l < 0) {
            return -1;
        }
        if (l > 0) {
            return 1;
        }
        return 0;
    }
}
