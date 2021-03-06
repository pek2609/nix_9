package ua.com.alevel.examples.constructors;

import ua.com.alevel.MathSet;
import ua.com.alevel.TaskRunner;
import ua.com.alevel.util.NumberReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class NumberArraysConstructor implements TaskRunner {

    @Override
    public void run() {
        System.out.println("Input array of numbers");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Number[] numbers = NumberReaderUtil.readNumbersFromConsole(reader);
            System.out.println("Input another array of numbers");
            Number[] numbers1 = NumberReaderUtil.readNumbersFromConsole(reader);
            MathSet mathSet = new MathSet(numbers, numbers1);
            System.out.println("Your mathSet: \n" + mathSet);
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
