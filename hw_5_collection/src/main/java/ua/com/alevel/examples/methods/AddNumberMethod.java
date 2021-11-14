package ua.com.alevel.examples.methods;

import ua.com.alevel.MathSet;
import ua.com.alevel.TaskRunner;
import ua.com.alevel.util.NumberReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class AddNumberMethod implements TaskRunner {

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MathSet mathSet = new MathSet();
        System.out.println("Input your number");
        try {
            mathSet.add(NumberReaderUtil.readNumberFromConsole(reader));
            System.out.println("mathSet = " + mathSet);
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
