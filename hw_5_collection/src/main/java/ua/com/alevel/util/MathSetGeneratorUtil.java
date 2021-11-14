package ua.com.alevel.util;

import ua.com.alevel.MathSet;

import java.util.Random;

public final class MathSetGeneratorUtil {

    private MathSetGeneratorUtil() {
    }

    public static void generateMathSet(MathSet mathSet, int size) {
        for (int i = 0; i < size; i++) {
            Random random = new Random();
            int numb = random.nextInt(100);
            Number number;
            if (numb % 2 == 0) {
                number = numb;
            } else {
                number = (double) numb;
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


}
