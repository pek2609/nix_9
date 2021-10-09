package ua.com.alevel;


import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        int startTime = 540, oddBreak = 5, evenBreak = 15, lessonTime = 45;
        Scanner scanner = new Scanner(System.in);
        int numberOfLesson;
        while (true) {
            System.out.println("Enter number from(1-10): ");
            try {
                numberOfLesson = Integer.parseInt(scanner.nextLine());
                if (numberOfLesson <= 10 && numberOfLesson >= 1) {
                    break;
                } else {
                    System.out.println("Your number must be in interval [1;10]. Try again..");
                }

            } catch (Exception e) {
                System.out.println(e.toString() + "\nTry again...");
            }
        }
        int endTime = startTime + numberOfLesson * lessonTime + numberOfLesson / 2 * oddBreak + (numberOfLesson - 1) / 2 * evenBreak;
        System.out.println("Time after " + numberOfLesson + " lesson = " + endTime / 60 + ":" + endTime % 60);
    }
}
