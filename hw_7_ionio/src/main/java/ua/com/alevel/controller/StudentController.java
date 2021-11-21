package ua.com.alevel.controller;


import ua.com.alevel.TaskRunner;
import ua.com.alevel.dto.student.StudentRequestDto;
import ua.com.alevel.dto.student.StudentResponseDto;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.facade.impl.StudentFacadeImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class StudentController implements TaskRunner {

    private static final String menuString = """
            -------------Menu-------------
            1 - create student
            2 - update student
            3 - delete student
            4 - findById
            5 - findAll
            0 - Back
            ------------------------------
            """;

    private final StudentFacade studentFacade = new StudentFacadeImpl();

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

    public void findAll() {
        try {
            Collection<StudentResponseDto> students = studentFacade.findAll();
            if (students.isEmpty()) {
                System.out.println("There is no students yet");
            } else {
                for (StudentResponseDto student : students) {
                    System.out.println(student.toString());
                }
            }
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.println("Please, enter id of student");
            String id = reader.readLine();
            StudentResponseDto student = studentFacade.findById(id);
            System.out.println("student = " + student.toString());
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }


    }

    private void delete(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            studentFacade.delete(id);
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println("Please, enter new first name");
            String firstName = reader.readLine();
            System.out.println("Please, enter new last name");
            String lastName = reader.readLine();
            StudentRequestDto studentRequestDto = new StudentRequestDto(firstName, lastName);
            studentFacade.update(studentRequestDto, id);
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void create(BufferedReader reader) {
        try {
            System.out.println("Please, enter first name");
            String firstName = reader.readLine();
            System.out.println("Please, enter last name");
            String lastName = reader.readLine();
            StudentRequestDto studentRequestDto = new StudentRequestDto(firstName, lastName);
            studentFacade.create(studentRequestDto);
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }
}