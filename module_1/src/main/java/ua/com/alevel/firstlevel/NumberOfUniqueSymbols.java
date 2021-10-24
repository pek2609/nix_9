package ua.com.alevel.firstlevel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class NumberOfUniqueSymbols {

    public void run() {
        while (true) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the array of numbers through spaces:");
            try {
                String string = bufferedReader.readLine();
                int[] numbers = getNumbersFromString(string);
                int numberOfUniqueSymbols = findNumberOfUniqueSymbols(numbers);
                System.out.println("numberOfUniqueSymbols = " + numberOfUniqueSymbols);
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static int[] getNumbersFromString(String string) {
        String[] array = string.split(" ");
        int[] numbers = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            numbers[i] = Integer.parseInt(array[i]);
        }
        return numbers;
    }

    private static int findNumberOfUniqueSymbols(int[] numbers) {
        Set<Integer> integers = new HashSet<>();
        for (int number : numbers) {
            integers.add(number);
        }
        return integers.size();
    }
}
