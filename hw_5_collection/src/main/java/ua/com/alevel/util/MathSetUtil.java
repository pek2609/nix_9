package ua.com.alevel.util;

import ua.com.alevel.MathSet;

import java.text.NumberFormat;
import java.text.ParseException;

public final class MathSetUtil {

    private MathSetUtil() {
    }

    public static void generateMathSet(MathSet mathSet , int size) {
        for (int i = 0; i < size; i++) {
            Number number;
            if (i % 2 == 0) {
                number = i;
            } else {
                number = (double) i;
            }
            mathSet.add(number);
        }
    }

    public static MathSet generateMathSet(int size, int maxCapacity) {
        MathSet mathSet = new MathSet();
        mathSet.setMaxCapacity(maxCapacity);
        generateMathSet(mathSet, size);
        return mathSet;
    }

    public static Number[] convertStringToArray(String numbers) throws ParseException {
        numbers = numbers.replace(".", ",");
        String[] strings = numbers.split(" ");
        Number[] numberArray = new Number[strings.length];
        for (int i = 0; i < strings.length; i++) {
            numberArray[i] = NumberFormat.getNumberInstance().parse(strings[i]);
        }
        return numberArray;
    }


}
