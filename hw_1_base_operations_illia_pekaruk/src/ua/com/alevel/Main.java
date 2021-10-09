package ua.com.alevel;

import ua.com.alevel.tasks.*;

public class Main {
    public static void main(String[] args) {
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        Task3 task3 = new Task3();

        task1.run();
        task3.run();
    }
}
