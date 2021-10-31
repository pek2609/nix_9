package ua.com.alevel;

import ua.com.alevel.firstlevel.trianglesquare.TriangleSquare;
import ua.com.alevel.firstlevel.uniquenumbers.NumberOfUniqueNumbers;
import ua.com.alevel.firstlevel.knightmove.KnightMove;
import ua.com.alevel.secondlevel.BinaryTree;
import ua.com.alevel.secondlevel.CorrectString;
import ua.com.alevel.thirdlevel.GameOfLife;

import java.util.ArrayList;
import java.util.List;

public class FirstModuleMain {
    public static void main(String[] args) {
        List<TaskRunner> taskRunnerList = new ArrayList<>();
        taskRunnerList.add(new NumberOfUniqueNumbers());
        taskRunnerList.add(new KnightMove());
        taskRunnerList.add(new TriangleSquare());
        taskRunnerList.add(new CorrectString());
        taskRunnerList.add(new BinaryTree());
        taskRunnerList.add(new GameOfLife());
        ConsoleHelper.run(taskRunnerList);
    }
}
