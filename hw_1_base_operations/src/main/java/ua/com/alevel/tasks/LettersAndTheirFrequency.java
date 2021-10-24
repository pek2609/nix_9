package ua.com.alevel.tasks;


import ua.com.alevel.TaskRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class LettersAndTheirFrequency implements TaskRunner {

    public void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the string:");
        String enterString ="";
        char[] symbols = new char[0];
        try {
            enterString = bufferedReader.readLine();
            symbols = enterString.toCharArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<Character, Integer> characterIntegerMap = new TreeMap<>();
        for (char symbol : symbols) {
            if(Character.isLetter(symbol)){
                int lenDiff = enterString.length() - enterString.replace(Character.toString(symbol), "").length();
                characterIntegerMap.put(symbol, lenDiff);
            }
        }
        System.out.println(characterIntegerMap);
    }
}
