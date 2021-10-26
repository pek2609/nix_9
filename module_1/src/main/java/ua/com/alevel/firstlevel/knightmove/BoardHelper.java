package ua.com.alevel.firstlevel.knightmove;

import java.util.HashMap;
import java.util.Map;

public final class BoardHelper {

    private static final Map<String, Integer> map = new HashMap<>();

    static {
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);
        map.put("E", 5);
        map.put("F", 6);
        map.put("G", 7);
        map.put("H", 8);
    }

    public static int getIndexByLetter(String letter) {
        return map.get(letter);
    }
}

