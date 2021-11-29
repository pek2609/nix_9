package ua.com.alevel;

import ua.com.alevel.examples.DateParserExample;
import ua.com.alevel.examples.FirstUniqueStringExample;
import ua.com.alevel.examples.LowestDistanceExample;

import java.io.IOException;
import java.util.*;

public class SecondModuleMain {

    private static final String CATALOG = "src/main/resources/inputdata/";

    public static void main(String[] args) throws IOException {
        List<TaskRunner> list = new ArrayList<>();
        list.add(new DateParserExample(CATALOG + "dateparser.txt"));
        list.add(new FirstUniqueStringExample(CATALOG + "uniquestrings.txt"));
        list.add(new LowestDistanceExample(CATALOG + "input.txt", CATALOG + "output.txt"));
        ConsoleHelper.run(list);
    }
}

