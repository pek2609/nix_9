package ua.com.alevel.reverseexamples;

import ua.com.alevel.StringReverseUtils;
import ua.com.alevel.TaskRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseWithIndexes implements TaskRunner {
    @Override
    public void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String src;
        int firstIndex, lastIndex;
        while (true) {
            try {
                System.out.println("Enter the string: ");
                src = bufferedReader.readLine();
                System.out.println("Enter the first index: ");
                firstIndex = Integer.parseInt(bufferedReader.readLine());
                System.out.println("Enter the last index: ");
                lastIndex = Integer.parseInt(bufferedReader.readLine());
                System.out.println("Reverse String = " + StringReverseUtils.reverse(src, firstIndex, lastIndex));
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
