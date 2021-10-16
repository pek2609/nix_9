package ua.com.alevel.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class SumOfDigitsInString {
    public void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the string:");
        char[] enterString = new char[0];
        try {
            enterString = bufferedReader.readLine().toCharArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sumOfDigits = 0;
        for (char c : enterString) {
            if (Character.isDigit(c)) {
                sumOfDigits += Character.getNumericValue(c);
            }
        }
        System.out.println("Sum of digits in the string = " + sumOfDigits);
    }
}
