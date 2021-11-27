package ua.com.alevel.tasks.uniquestrings;

import java.util.*;

public class FirstUniqueString {

    private final List<String> names;

    public FirstUniqueString(List<String> names) {
        this.names = names;
    }

    public String findFirstUniqueString() {
        if(names.isEmpty()){
            throw new RuntimeException("List is empty!");
        }
        for (int i = 1; i < names.size(); i++) {
            if(names.get(i).equals(names.get(i-1))){
                names.remove(i-1);
                i--;
            }
            else {
                return names.get(i);
            }
        }
        return names.get(0);
    }
}
