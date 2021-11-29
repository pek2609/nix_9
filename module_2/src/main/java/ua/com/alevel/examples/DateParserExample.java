package ua.com.alevel.examples;

import ua.com.alevel.TaskRunner;
import ua.com.alevel.tasks.dateparser.DateParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DateParserExample implements TaskRunner {

    private final String path;

    public DateParserExample(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            List<String> lines =new ArrayList<>();
            while (reader.ready()) {
                lines.add(reader.readLine());
            }
            DateParser dateParser = new DateParser(lines);
            List<String> parsedDates = dateParser.parse();
            System.out.println("Parsed dates:");
            parsedDates.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
