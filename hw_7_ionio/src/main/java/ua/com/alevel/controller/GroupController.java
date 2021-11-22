package ua.com.alevel.controller;

import ua.com.alevel.TaskRunner;
import ua.com.alevel.dto.group.GroupRequestDto;
import ua.com.alevel.dto.group.GroupResponseDto;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.facade.impl.GroupFacadeImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class GroupController implements TaskRunner {

    private static final String menuString = """
            -------------Menu-------------
            1 - create group
            2 - update group
            3 - delete group
            4 - findById
            5 - findAll
            0 - Back
            ------------------------------
            """;

    private final GroupFacade groupFacade = new GroupFacadeImpl();

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int op;
        while (true) {
            try {
                System.out.println(menuString);
                System.out.println("Enter your option");
                op = Integer.parseInt(reader.readLine());
                if (op == 0) {
                    break;
                }
                callByOption(reader, op);
            } catch (Exception e) {
                System.out.println("problem: = " + e.getMessage());
            }
        }
    }

    private void callByOption(BufferedReader reader, int op) {
        switch (op) {
            case 1 -> create(reader);
            case 2 -> update(reader);
            case 3 -> delete(reader);
            case 4 -> findById(reader);
            case 5 -> findAll();
            default -> System.out.println("Unexpected option..");
        }
    }

    private void findAll() {
        try {
            Collection<GroupResponseDto> groups = groupFacade.findAll();
            if (groups.isEmpty()) {
                System.out.println("There is no groups yet");
            } else {
                for (GroupResponseDto group : groups) {
                    System.out.println(group.toString());
                }
            }
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.println("Please, enter id of group");
            String id = reader.readLine();
            GroupResponseDto group = groupFacade.findById(id);
            System.out.println("group = " + group.toString());
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            groupFacade.delete(id);
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println("Please, enter new group name");
            String name = reader.readLine();
            System.out.println("Please, enter new teacher name");
            String teacherName = reader.readLine();
            GroupRequestDto groupRequestDto = new GroupRequestDto(name, teacherName);
            groupFacade.update(groupRequestDto, id);
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void create(BufferedReader reader) {
        try {
            System.out.println("Please, enter group name");
            String name = reader.readLine();
            System.out.println("Please, enter teacher name");
            String teacherName = reader.readLine();
            GroupRequestDto groupRequestDto = new GroupRequestDto(name, teacherName);
            groupFacade.create(groupRequestDto);
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

}
