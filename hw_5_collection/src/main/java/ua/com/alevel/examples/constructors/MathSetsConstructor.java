package ua.com.alevel.examples.constructors;

import ua.com.alevel.MathSet;
import ua.com.alevel.TaskRunner;
import ua.com.alevel.util.MathSetUtil;
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
            String numbersString = reader.readLine();
            Number[] numbers = MathSetUtil.convertStringToArray(numbersString);
            MathSet mathSetFirst = new MathSet(numbers);
            System.out.println("Input another array of numbers");
            numbersString = reader.readLine();
            Number[] numbers1 = MathSetUtil.convertStringToArray(numbersString);
            MathSet mathSetSecond = new MathSet(numbers1);
            MathSet res = new MathSet(mathSetFirst, mathSetSecond);
            System.out.println("Your result mathSet: \n" + res.toString());
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
