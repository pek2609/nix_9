package ua.com.alevel.dateformats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Formats {

    private static Formats instance;
    private final List<String> formats;
    private final Map<String, Integer> months;

    public static Formats getInstance() {
        if (instance == null) {
            instance = new Formats();
        }
        return instance;
    }

    public List<String> getFormats() {
        return formats;
    }

    public Map<String, Integer> getMonths() {
        return months;
    }

    private Formats() {
        formats = new ArrayList<>();
        formats.add("dd/mm/yyyy");
        formats.add("dd/mm/yyyy 00:00");
        formats.add("dd/mm/yyyy 00:00:00:000");
        formats.add("dd/mm/yy");
        formats.add("dd/mm/yy 00:00");
        formats.add("dd/mm/yy 00:00:00:000");
        formats.add("dd-mm-yyyy");
        formats.add("dd-mm-yyyy 00:00");
        formats.add("dd-mm-yyyy 00:00:00:000");
        formats.add("dd-mm-yy");
        formats.add("dd-mm-yy 00:00");
        formats.add("dd-mm-yy 00:00:00:000");
        formats.add("mm-dd-yyyy");
        formats.add("mm-dd-yyyy 00:00");
        formats.add("mm-dd-yyyy 00:00:00:000");
        formats.add("m/d/yyyy");
        formats.add("m/d/yyyy 00:00");
        formats.add("m/d/yyyy 00:00:00:000");
        formats.add("d/m/yyyy");
        formats.add("d/m/yyyy 00:00");
        formats.add("d/m/yyyy 00:00:00:000");
        formats.add("m-d-yyyy");
        formats.add("m-d-yyyy 00:00");
        formats.add("m-d-yyyy 00:00:00:000");
        formats.add("d-m-yyyy");
        formats.add("d-m-yyyy 00:00");
        formats.add("d-m-yyyy 00:00:00:000");
        formats.add("dd-mmm-yyyy");
        formats.add("dd-mmm-yyyy 00:00");
        formats.add("dd-mmm-yyyy 00:00:00:000");
        formats.add("mmm-d-yy");
        formats.add("mmm-d-yy 00:00");
        formats.add("mmm-d-yy 00:00:00:000");

        months = new HashMap<>();
        months.put("Январь", 1);
        months.put("January", 1);
        months.put("Февраль", 2);
        months.put("February", 2);
        months.put("Март", 3);
        months.put("March", 3);
        months.put("Апрель", 4);
        months.put("April", 4);
        months.put("Май", 5);
        months.put("May", 5);
        months.put("Июнь", 6);
        months.put("June", 6);
        months.put("Июль", 7);
        months.put("Jule", 7);
        months.put("Август", 8);
        months.put("August", 8);
        months.put("Сентябрь", 9);
        months.put("September", 9);
        months.put("Октябрь", 10);
        months.put("October", 10);
        months.put("Ноябрь", 11);
        months.put("November", 11);
        months.put("Декабрь", 12);
        months.put("December", 12);
    }
}
