package ua.com.alevel.util;

import ua.com.alevel.MathSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;

public final class MathSetReaderUtil {

    private MathSetReaderUtil() {

    }

    public static MathSet[] readMathSetsFromConsole(BufferedReader reader) throws IOException, ParseException {
        System.out.println("Input number of MathSets: ");
        int size = Integer.parseInt(reader.readLine());
        if (size < 0) {
            throw new RuntimeException("Your size must be >=0");
        }
        MathSet[] mathSets = new MathSet[size];
        for (int i = 0; i < mathSets.length; i++) {
            System.out.println("Input " + (i + 1) + " mathSet");
            mathSets[i] = new MathSet(NumberReaderUtil.readNumbersFromConsole(reader));
        }
        return mathSets;
    }
}
