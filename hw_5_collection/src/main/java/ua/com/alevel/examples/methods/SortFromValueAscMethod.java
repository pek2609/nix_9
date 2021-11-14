package ua.com.alevel.examples.methods;

import ua.com.alevel.MathSet;
import ua.com.alevel.TaskRunner;
import ua.com.alevel.util.NumberReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class SortFromValueAscMethod implements TaskRunner {

    @Override
    public void run() {
        System.out.println("Input your mathSet");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            MathSet mathSet = new MathSet(NumberReaderUtil.readNumbersFromConsole(reader));
            System.out.println("Input your number");
            Number number = NumberReaderUtil.readNumberFromConsole(reader);
            mathSet.sortAsc(number);
            System.out.println("mathSet = " + mathSet);
        } catch (IOException | ParseException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
