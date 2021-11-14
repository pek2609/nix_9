package ua.com.alevel.examples.constructors;

import ua.com.alevel.MathSet;
import ua.com.alevel.TaskRunner;
import ua.com.alevel.util.MathSetUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class NumberArrayConstructor implements TaskRunner {
    @Override
    public void run() {
        System.out.println("Input array of numbers");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String numbersString = reader.readLine();
            Number[] numbers = MathSetUtil.convertStringToArray(numbersString);
            MathSet mathSet = new MathSet(numbers);
            System.out.println("Your mathSet: \n" + mathSet.toString());
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
