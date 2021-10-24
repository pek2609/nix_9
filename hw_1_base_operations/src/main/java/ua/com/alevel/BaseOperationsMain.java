package ua.com.alevel;

import ua.com.alevel.tasks.*;

import java.util.ArrayList;
import java.util.List;

public class BaseOperationsMain {

    public static void main(String[] args) {
        List<TaskRunner> taskRunnerList = new ArrayList<>();
        taskRunnerList.add(new SumOfDigitsInString());
        taskRunnerList.add(new LettersAndTheirFrequency());
        taskRunnerList.add(new EndOfLesson());
        ConsoleHelper.run(taskRunnerList);
    }
}
