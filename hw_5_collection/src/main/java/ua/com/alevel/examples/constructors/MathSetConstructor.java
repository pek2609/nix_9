package ua.com.alevel.examples.constructors;

import ua.com.alevel.MathSet;
import ua.com.alevel.TaskRunner;
import ua.com.alevel.util.MathSetGeneratorUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MathSetConstructor implements TaskRunner {
    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Input size of MathSet: ");
            int size = Integer.parseInt(reader.readLine());
            if (size < 0) {
                throw new RuntimeException("Your size must be >=0");
            }
            MathSet mathSet = new MathSet();
            System.out.println("Generating mathSet with N numbers, where N = " + size);
            MathSetGeneratorUtil.generateMathSet(mathSet, size);
            System.out.println(mathSet);
            System.out.println("Creating new mathSet from first");
            MathSet other = new MathSet(mathSet);
            System.out.println("new mathSet = " + other);
        } catch (IOException | RuntimeException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
