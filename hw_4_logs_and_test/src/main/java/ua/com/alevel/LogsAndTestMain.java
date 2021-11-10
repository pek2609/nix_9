package ua.com.alevel;

import ua.com.alevel.controller.AuthorController;
import ua.com.alevel.controller.BookController;
import java.util.ArrayList;
import java.util.List;


public class LogsAndTestMain {
    public static void main(String[] args) {
        List<TaskRunner> list = new ArrayList<>();
        list.add(new AuthorController());
        list.add(new BookController());
        ConsoleHelper.run(list);
    }
}
