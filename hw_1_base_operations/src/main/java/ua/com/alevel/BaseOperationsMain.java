package ua.com.alevel;

import ua.com.alevel.tasks.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class BaseOperationsMain {

    private static final String MENU_STRING = """
            -----------HW_1_Base_Operations-----------
            Task1 - 1
            Task2 - 2
            Task3 - 3
            Exit  - 0
            ------------------------------------------
            Enter your option:""";


    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        SumOfDigitsInString sumOfDigitsInString = new SumOfDigitsInString();
        LettersAndTheirFrequency lettersAndTheirFrequency = new LettersAndTheirFrequency();
        EndOfLesson endOfLesson = new EndOfLesson();
        while (true) {
            System.out.println(MENU_STRING);
            try {
                int op = Integer.parseInt(bufferedReader.readLine());
                switch (op) {
                    case 1 -> sumOfDigitsInString.run();
                    case 2 -> lettersAndTheirFrequency.run();
                    case 3 -> endOfLesson.run();
                    case 0 -> { return; }
                    default -> System.out.println("Unexpected option...");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
