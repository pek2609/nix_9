package ua.com.alevel.examples;

import ua.com.alevel.TaskRunner;
import ua.com.alevel.tasks.dateparser.DateParser;
import ua.com.alevel.tasks.uniquestrings.FirstUniqueString;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FirstUniqueStringExample implements TaskRunner {

    private final String path;

    public FirstUniqueStringExample(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            List<String> names = new ArrayList<>();
            while (reader.ready()) {
                names.add(reader.readLine());
            }
            FirstUniqueString firstUniqueString = new FirstUniqueString(names);
            String firstUniqueName = firstUniqueString.findFirstUniqueString();
            System.out.println("firstUniqueName = " + firstUniqueName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
