package ua.com.alevel.examples.methods;

import ua.com.alevel.MathSet;
import ua.com.alevel.TaskRunner;
import ua.com.alevel.util.NumberReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class AddNumbersMethod implements TaskRunner {

    @Override
    public void run() {
        System.out.println("Input array of numbers");
        MathSet mathSet = new MathSet();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            mathSet.add(NumberReaderUtil.readNumbersFromConsole(reader));
            System.out.println("Your mathSet: \n" + mathSet);
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
