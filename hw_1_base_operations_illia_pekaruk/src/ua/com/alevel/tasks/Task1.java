package ua.com.alevel.tasks;

import java.util.Scanner;

public class Task1 {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the string:");
        char[] string = scanner.nextLine().toCharArray();
        int sum = 0;
        for (int i = 0; i < string.length; i++) {
            if (Character.isDigit(string[i])) {
                sum += Character.getNumericValue(string[i]);
            }
        }
        System.out.println("Sum of digits in the string = " + sum);
    }
}
