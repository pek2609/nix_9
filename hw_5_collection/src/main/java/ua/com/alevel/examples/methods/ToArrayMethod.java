package ua.com.alevel.examples.methods;

import ua.com.alevel.MathSet;
import ua.com.alevel.TaskRunner;
import ua.com.alevel.util.NumberReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class ToArrayMethod implements TaskRunner {
    @Override
    public void run() {
        System.out.println("Input your mathSet");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            MathSet mathSet = new MathSet(NumberReaderUtil.readNumbersFromConsole(reader));
            Number[] numbers = mathSet.toArray();
            System.out.println("Array of numbers after calling toArray");
            for (Number number : numbers) {
                System.out.print(number + " ");
            }
            System.out.println();
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
