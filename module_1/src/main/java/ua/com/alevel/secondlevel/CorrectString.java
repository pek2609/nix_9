package ua.com.alevel.secondlevel;

import ua.com.alevel.TaskRunner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;

public class CorrectString implements TaskRunner {

    Map<Character, Character> mapBrackets = new HashMap<>();
    Stack<Character> brackets = new Stack<>();

    public CorrectString() {
        mapBrackets.put('(', ')');
        mapBrackets.put('{', '}');
        mapBrackets.put('[', ']');
    }

    @Override
    public void run() {
        while (true) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter the string:");
                String res = bufferedReader.readLine();
                System.out.println(isCorrectString(res) ? "String " + res + " is correct!" : "String " + res + " is not correct!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isCorrectString(String str) {
        for (char c : str.toCharArray()) {
            Optional<Character> open = mapBrackets.keySet().stream().filter(character -> character.equals(c)).findFirst();
            open.ifPresent(character -> brackets.push(mapBrackets.get(character)));
            Optional<Character> close = mapBrackets.values().stream().filter(character -> character.equals(c)).findFirst();
            if (close.isPresent() && close.get().equals(brackets.peek())) {
                brackets.pop();
            }
        }
        return str.isBlank() || brackets.size() == 0;
    }
}
