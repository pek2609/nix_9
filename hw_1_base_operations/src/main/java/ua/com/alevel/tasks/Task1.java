package ua.com.alevel.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Task1 {
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
        for (int i = 0; i < enterString.length; i++) {
            if (Character.isDigit(enterString[i])) {
                sumOfDigits += Character.getNumericValue(enterString[i]);
            }
        }
        System.out.println("Sum of digits in the string = " + sumOfDigits);
    }
}
