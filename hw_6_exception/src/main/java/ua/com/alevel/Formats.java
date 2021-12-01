package ua.com.alevel;

import java.util.List;

public final class Formats {

    private static Formats instance;
    private final List<String> formats;

    public static Formats getInstance() {
        if (instance == null) {
            instance = new Formats();
        }
        return instance;
    }

    private Formats(){
        instance = new Formats();
        formats = null;
    }
}
