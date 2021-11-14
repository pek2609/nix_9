package ua.com.alevel.examples.constructors;

import ua.com.alevel.MathSet;
import ua.com.alevel.TaskRunner;
import ua.com.alevel.util.NumberReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class MathSetsConstructor implements TaskRunner {
    @Override
    public void run() {
        System.out.println("Input array of numbers");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            MathSet mathSetFirst = new MathSet(NumberReaderUtil.readNumbersFromConsole(reader));
            System.out.println("Input another array of numbers");
            MathSet mathSetSecond = new MathSet(NumberReaderUtil.readNumbersFromConsole(reader));
            MathSet res = new MathSet(mathSetFirst, mathSetSecond);
            System.out.println("Your result mathSet: \n" + res);
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
