package ua.com.alevel;

import ua.com.alevel.firstlevel.uniquenumbers.NumberOfUniqueNumbers;
import ua.com.alevel.firstlevel.knightmove.KnightMove;
import java.util.ArrayList;
import java.util.List;

public class FirstModuleMain {
    public static void main(String[] args) {
        List<TaskRunner> taskRunnerList = new ArrayList<>();
        taskRunnerList.add(new NumberOfUniqueNumbers());
        taskRunnerList.add(new KnightMove());
        ConsoleHelper.run(taskRunnerList);
    }
}
