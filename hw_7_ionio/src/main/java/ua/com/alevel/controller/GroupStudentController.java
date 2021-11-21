package ua.com.alevel.controller;

import ua.com.alevel.TaskRunner;
import ua.com.alevel.dto.groupstudent.GroupStudentRequestDto;
import ua.com.alevel.dto.groupstudent.GroupStudentResponseDto;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.facade.GroupStudentFacade;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.facade.impl.GroupFacadeImpl;
import ua.com.alevel.facade.impl.GroupStudentFacadeImpl;
import ua.com.alevel.facade.impl.StudentFacadeImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class GroupStudentController implements TaskRunner {

    private static final String menuString = """
            -------------Menu-------------
            1 - enroll student in group
            2 - update record
            3 - exclude a student from the group
            4 - find record
            5 - find all records`
            0 - Back
            ------------------------------
            """;

    private final GroupFacade groupFacade = new GroupFacadeImpl();
    private final StudentFacade studentFacade = new StudentFacadeImpl();
    private final GroupStudentFacade groupStudentFacade = new GroupStudentFacadeImpl();


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
            Collection<GroupStudentResponseDto> records = groupStudentFacade.findAll();
            if (records.isEmpty()) {
                System.out.println("There is no records yet");
            } else {
                for (GroupStudentResponseDto record : records) {
                    System.out.println(record.toString());
                }
            }
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.println("Please, enter id of record");
            String id = reader.readLine();
            GroupStudentResponseDto record = groupStudentFacade.findById(id);
            System.out.println("record = " + record.toString());
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }


    }

    private void delete(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            groupStudentFacade.delete(id);
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            groupFacade.findAll();
            System.out.println("Please, enter new group id");
            String groupId = reader.readLine();
            studentFacade.findAll();
            System.out.println("Please, enter new student id");
            String studentId = reader.readLine();
            GroupStudentRequestDto record = new GroupStudentRequestDto(groupId, studentId);
            groupStudentFacade.update(record, id);
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void create(BufferedReader reader) {
        try {
            groupFacade.findAll();
            System.out.println("Please, enter new group id");
            String groupId = reader.readLine();
            studentFacade.findAll();
            System.out.println("Please, enter new student id");
            String studentId = reader.readLine();
            GroupStudentRequestDto record = new GroupStudentRequestDto(groupId, studentId);
            groupStudentFacade.create(record);
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

}

