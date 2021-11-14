package ua.com.alevel.examples.methods;

import ua.com.alevel.MathSet;
import ua.com.alevel.TaskRunner;
import ua.com.alevel.util.NumberReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class JoinMathSetMethod implements TaskRunner {

    @Override
    public void run() {
        System.out.println("Input your mathSet");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            MathSet mathSetSrc = new MathSet(NumberReaderUtil.readNumbersFromConsole(reader));
            System.out.println("Input another mathSet");
            MathSet mathSetJoined = new MathSet(NumberReaderUtil.readNumbersFromConsole(reader));
            mathSetSrc.join(mathSetJoined);
            System.out.println("Your joined mathSets mathSet: \n" + mathSetSrc);
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
