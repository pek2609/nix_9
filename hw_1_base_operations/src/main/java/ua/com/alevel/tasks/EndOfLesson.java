package ua.com.alevel.tasks;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EndOfLesson {
    public void run() {
        int startTime = 540, oddBreak = 5, evenBreak = 15, lessonTime = 45, numberOfLesson;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter number from(1-10): ");
            try {
                numberOfLesson = Integer.parseInt(bufferedReader.readLine());
                if (numberOfLesson <= 10 && numberOfLesson >= 1) {
                    break;
                } else {
                    System.out.println("Your number must be in interval [1;10]. Try again..");
                }

            } catch (Exception e) {
                System.out.println(e + "\nTry again...");
            }
        }
        int endTime = startTime + numberOfLesson * lessonTime + numberOfLesson / 2 * oddBreak + (numberOfLesson - 1) / 2 * evenBreak;
        System.out.println("Time after " + numberOfLesson + " lesson = " + endTime / 60 + ":" + endTime % 60);
    }
}
