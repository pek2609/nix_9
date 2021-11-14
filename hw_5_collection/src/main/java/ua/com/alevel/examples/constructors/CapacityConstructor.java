package ua.com.alevel.examples.constructors;

import ua.com.alevel.MathSet;
import ua.com.alevel.TaskRunner;
import ua.com.alevel.util.MathSetGeneratorUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CapacityConstructor implements TaskRunner {

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Input max capacity of MathSet: ");
            int maxCapacity = Integer.parseInt(reader.readLine());
            if (maxCapacity < 0) {
                throw new RuntimeException("Your capacity must be >=0");
            }
            MathSet mathSet = MathSetGeneratorUtil.generateMathSet(maxCapacity, maxCapacity);
            System.out.println("Generating mathSet with N numbers, where N = maxCapacity");
            System.out.println(mathSet);
            System.out.println("Trying to add new element");
            mathSet.add(6.2);
        } catch (IOException | RuntimeException exception) {
            System.out.println(exception.getMessage());
        }

    }
}
