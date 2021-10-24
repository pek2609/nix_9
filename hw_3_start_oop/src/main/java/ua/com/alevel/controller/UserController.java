package ua.com.alevel.controller;

import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;
import ua.com.alevel.util.UserList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserController {

    private static final String menuString = """
            -------------Menu-------------
            1 - create user
            2 - update user
            3 - delete user
            4 - findById
            5 - findAll
            0 - Exit
            ------------------------------
            """;

    private final UserService userService = new UserService();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int op;
        while (true) {
            try {
                System.out.println(menuString);
                System.out.println("Enter your option");
                op = Integer.parseInt(reader.readLine());
                callByOption(reader, op);
            } catch (Exception e) {
                System.out.println("problem: = " + e.getMessage());
            }
        }
    }

    private void callByOption(BufferedReader reader, int op){
        switch (op){
           case 1 -> create(reader);
           case 2 -> update(reader);
           case 3 -> delete(reader);
           case 4 -> findById(reader);
           case 5 -> findAll();
           case 0 -> System.exit(0);
           default -> System.out.println("Unexpected option..");
        }
    }

    private void findAll() {
        UserList users = userService.findAll();
        if (users != null && users.size() != 0) {
            users.print();
        } else {
            System.out.println("users empty");
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            User user = userService.findById(id);
            System.out.println("user = " + user);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            userService.delete(id);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println("Please, enter your name");
            String name = reader.readLine();
            System.out.println("Please, enter your age");
            int age = Integer.parseInt(reader.readLine());
            User user = new User();
            user.setId(id);
            user.setAge(age);
            user.setName(name);
            userService.update(user);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void create(BufferedReader reader) {
        try {
            System.out.println("Please, enter your name");
            String name = reader.readLine();
            System.out.println("Please, enter your age");
            int age = Integer.parseInt(reader.readLine());
            User user = new User();
            user.setAge(age);
            user.setName(name);
            userService.create(user);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }
}
