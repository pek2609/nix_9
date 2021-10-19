package ua.com.alevel.reverseexamples;

import ua.com.alevel.StringReverseUtils;
import ua.com.alevel.TaskRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleReverse implements TaskRunner {
    @Override
    public void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String src;
        while (true) {
            System.out.println("Enter the string: ");
            try {
                src = bufferedReader.readLine();
                System.out.println("Reverse String = " + StringReverseUtils.reverse(src));
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
