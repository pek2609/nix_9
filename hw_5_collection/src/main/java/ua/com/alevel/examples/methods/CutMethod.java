package ua.com.alevel.examples.methods;

import ua.com.alevel.MathSet;
import ua.com.alevel.TaskRunner;
import ua.com.alevel.util.NumberReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class CutMethod implements TaskRunner {

    @Override
    public void run() {
        System.out.println("Input your mathSet");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            MathSet mathSet = new MathSet(NumberReaderUtil.readNumbersFromConsole(reader));
            System.out.println("Input interval , which you want to cut: ");
            System.out.println("Input start index");
            int firstIndex = Integer.parseInt(reader.readLine());
            System.out.println("Input end index");
            int lastIndex = Integer.parseInt(reader.readLine());
            MathSet cutMathSet = mathSet.cut(firstIndex, lastIndex);
            System.out.println("mathSet = " + mathSet);
            System.out.println("cutMathSet = " + cutMathSet);
        } catch (IOException | ParseException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
