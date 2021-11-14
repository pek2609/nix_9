package ua.com.alevel.examples.methods;

import ua.com.alevel.MathSet;
import ua.com.alevel.TaskRunner;
import ua.com.alevel.util.NumberReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class ClearNumbersMethod implements TaskRunner {

    @Override
    public void run() {
        System.out.println("Input your mathSet");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            MathSet mathSet = new MathSet(NumberReaderUtil.readNumbersFromConsole(reader));
            System.out.println("Input array of numbers");
            Number[] numbers = NumberReaderUtil.readNumbersFromConsole(reader);
            mathSet.clear(numbers);
            System.out.println("Your mathSet: \n" + mathSet);
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
