package ua.com.alevel;

import ua.com.alevel.controller.GroupController;
import ua.com.alevel.controller.GroupStudentController;
import ua.com.alevel.controller.StudentController;

import java.util.ArrayList;
import java.util.List;

public class IoNioMain {

    public static void main(String[] args) {
        List<TaskRunner> list = new ArrayList<>();
        list.add(new GroupController());
        list.add(new StudentController());
        list.add(new GroupStudentController());
        ConsoleHelper.run(list);
    }
}
