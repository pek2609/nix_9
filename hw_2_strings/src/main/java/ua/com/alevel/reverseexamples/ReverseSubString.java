package ua.com.alevel.reverseexamples;

import ua.com.alevel.StringReverseUtils;
import ua.com.alevel.TaskRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseSubString implements TaskRunner {
    @Override
    public void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String src, dest;
        while (true) {
            try {
                System.out.println("Enter the source string: ");
                src = bufferedReader.readLine();
                System.out.println("Enter the subString: ");
                dest = bufferedReader.readLine();
                System.out.println("Reverse String = " + StringReverseUtils.reverse(src, dest));
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
