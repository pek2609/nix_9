package ua.com.alevel.util;

import ua.com.alevel.persistence.entity.Promotion;

import java.util.Calendar;
import java.util.Date;

public final class PriceAndDateUtil {

    private PriceAndDateUtil() {
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
        return adults * priceForOne + children * priceForOne / 2;
    }
}
