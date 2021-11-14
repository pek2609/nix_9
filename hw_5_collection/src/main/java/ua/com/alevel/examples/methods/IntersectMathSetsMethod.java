package ua.com.alevel.examples.methods;

import ua.com.alevel.MathSet;
import ua.com.alevel.TaskRunner;
import ua.com.alevel.util.MathSetReaderUtil;
import ua.com.alevel.util.NumberReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class IntersectMathSetsMethod implements TaskRunner {

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input your mathSet");
        try {
            MathSet mathSetSrc = new MathSet(NumberReaderUtil.readNumbersFromConsole(reader));
            mathSetSrc.intersection(MathSetReaderUtil.readMathSetsFromConsole(reader));
            System.out.println("mathSetSrc = " + mathSetSrc);
        } catch (IOException | ParseException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
