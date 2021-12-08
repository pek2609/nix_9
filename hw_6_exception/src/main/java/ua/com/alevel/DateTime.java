package ua.com.alevel;

public class DateTime {

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
}
