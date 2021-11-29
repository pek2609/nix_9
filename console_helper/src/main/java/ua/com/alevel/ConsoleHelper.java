package ua.com.alevel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class ConsoleHelper {

    public static void run(List<TaskRunner> taskRunners) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println(createMenuString(taskRunners));
            try {
                int op = Integer.parseInt(bufferedReader.readLine());
                runTaskByOption(taskRunners, op);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static StringBuffer createMenuString(List<TaskRunner> taskRunners) {
        StringBuffer menuString = new StringBuffer("-----------MENU-----------\n");
        for (int i = 0; i < taskRunners.size(); i++) {
            menuString.append(taskRunners.get(i).getClass().getSimpleName()).append(" - ").append(i + 1).append('\n');
        }
        menuString.append("Exit - 0\n").append("--------------------------\n").append("Enter your option:");
        return menuString;
    }

    private static void runTaskByOption(List<TaskRunner> taskRunners, int op) {
        if ((op > 0 && op <= taskRunners.size())) {
            taskRunners.get(op - 1).run();
        } else if (op == 0) {
            System.exit(0);
        } else {
            System.out.println("Unexpected option...\n");
        }
    }
}
