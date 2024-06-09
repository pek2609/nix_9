package ua.com.alevel.util;

import ua.com.alevel.persistence.entity.Promotion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public final class PriceAndDateUtil {

    private static final String DEFAULT_DATE_TIME_SHORT_PATTERN = "dd.MM.yyyy hh:mm";
    private static final String DEFAULT_DATE_PATTERN = "dd.MM.yyyy";
    private static final String EMPTY_PLACEHOLDER = "-";

    private PriceAndDateUtil() {
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return Optional.ofNullable(localDateTime)
                .map(ldt -> ldt.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_SHORT_PATTERN)))
                .orElse(EMPTY_PLACEHOLDER);
    }

    public static String formatLocalDate(LocalDate localDate) {
        return Optional.ofNullable(localDate)
                .map(ld -> ld.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN)))
                .orElse(EMPTY_PLACEHOLDER);
    }


    public static Date addOneDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }

    public static Double countPrice(int adults, int children, double priceForOne, Promotion promotion) {
        if (promotion != null && promotion.isActive()) {
            priceForOne = priceForOne - priceForOne * promotion.getPercent() / 100;
        }
        return (adults + children) * priceForOne;
    }

    public static Double countPrice(int adults, int children, double priceForOne) {
        return countPrice(adults, children, priceForOne, null);
    }
}
