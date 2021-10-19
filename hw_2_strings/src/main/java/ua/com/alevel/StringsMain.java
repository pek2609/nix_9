package ua.com.alevel;

import ua.com.alevel.reverseexamples.*;

import java.util.ArrayList;
import java.util.List;

public class StringsMain {
    public static void main(String[] args) {
        List<TaskRunner> taskRunnerList = new ArrayList<>();
        taskRunnerList.add(new SimpleReverse());
        taskRunnerList.add(new ReverseByWord());
        taskRunnerList.add(new ReverseSubString());
        taskRunnerList.add(new ReverseWithIndexes());
        ConsoleHelper.run(taskRunnerList);
    }
}
