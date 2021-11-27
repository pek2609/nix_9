package ua.com.alevel;

import ua.com.alevel.tasks.uniquestrings.FirstUniqueString;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String PATH = "src/main/resources/inputdata/uniquestrings.txt";

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH));
        List<String> list = new ArrayList<>();
        while (bufferedReader.ready()) {
            list.add(bufferedReader.readLine());
        }
        FirstUniqueString firstUniqueString = new FirstUniqueString(list);
        try {
            System.out.println(firstUniqueString.findFirstUniqueString());
        } catch (RuntimeException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }
}

