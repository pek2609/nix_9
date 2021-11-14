package ua.com.alevel.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

public final class NumberReaderUtil {

    private NumberReaderUtil() {
    }

    public static Number[] readNumbersFromConsole(BufferedReader reader) throws IOException, ParseException {
        String numbersString = reader.readLine();
        return convertStringToArray(numbersString);
    }

    public static Number readNumberFromConsole(BufferedReader reader) throws IOException, ParseException {
        String numberString = reader.readLine();
        numberString = numberString.replace('.', ',');
        return NumberFormat.getInstance().parse(numberString);
    }

    public static Number[] convertStringToArray(String numbers) throws ParseException {
        numbers = numbers.replace(".", ",");
        String[] strings = numbers.split(" ");
        Number[] numberArray = new Number[strings.length];
        for (int i = 0; i < strings.length; i++) {
            numberArray[i] = NumberFormat.getInstance().parse(strings[i]);
        }
        return numberArray;
    }
}
