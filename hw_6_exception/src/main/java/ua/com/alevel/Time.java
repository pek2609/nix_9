package ua.com.alevel;

public class Time {

    private static final int MIN_HOUR = 0;
    private static final int MAX_HOUR = 23;
    private static final int MIN_MINUTE = 0;
    private static final int MAX_MINUTE = 59;
    private static final int MIN_SECOND = 0;
    private static final int MAX_SECOND = 59;
    private static final int MIN_MS = 0;
    private static final int MAX_MS = 999;

    private int hour;
    private int minute;
    private int second;
    private int ms;

    public Time() {
        this(MIN_HOUR, MIN_MINUTE, MIN_SECOND, MIN_MS);
    }

    public Time(int hour, int minute, int second, int ms) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.ms = ms;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMs() {
        return ms;
    }

    public void setMs(int ms) {
        this.ms = ms;
    }

    public boolean isValid() {
        return hour >= MIN_HOUR && hour <= MAX_HOUR
                && minute >= MIN_MINUTE && minute <= MAX_MINUTE
                && second >= MIN_SECOND && second <= MAX_SECOND
                && ms >= MIN_MS && ms <= MAX_MS;
    }
}
