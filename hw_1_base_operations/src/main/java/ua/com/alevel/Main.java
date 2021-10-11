package ua.com.alevel;

import ua.com.alevel.tasks.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

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
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        Task3 task3 = new Task3();
        while (true) {
            System.out.println(MENU_STRING);
            try {
                int op = Integer.parseInt(bufferedReader.readLine());
                switch (op) {
                    case 1 -> task1.run();
                    case 2 -> task2.run();
                    case 3 -> task3.run();
                    case 0 -> { return; }
                    default -> System.out.println("Unexpected option...");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
