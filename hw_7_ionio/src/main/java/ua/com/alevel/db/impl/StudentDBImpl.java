package ua.com.alevel.db.impl;

import ua.com.alevel.CSVReader;
import ua.com.alevel.CSVWriter;
import ua.com.alevel.db.StudentDB;
import ua.com.alevel.entity.Student;
import ua.com.alevel.util.GenerateIdUtil;
import ua.com.alevel.util.Parser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;

public class StudentDBImpl implements StudentDB {

    private static final String PATH_CSV = "hw_7_ionio/students.csv";
    private final File file = new File(PATH_CSV);
    private Collection<Student> students;
    private static StudentDBImpl instance;

    public static StudentDBImpl getInstance() {
        if (instance == null) {
            instance = new StudentDBImpl();
        }
        return instance;
    }

    private StudentDBImpl() {
        try {
            file.createNewFile();
            students = findAll();
        } catch (IOException e) {
            students = new LinkedHashSet<>();
        }
    }

    @Override
    public void create(Student entity) {
        entity.setId(GenerateIdUtil.generateId(students));
        students.add(entity);
        try (FileWriter fileWriter = new FileWriter(file)) {
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            csvWriter.writeAll(Parser.convertToStrings(Student.class, students));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Student entity) {
        Student byId = findById(entity.getId());
        byId.setFirstName(entity.getFirstName());
        byId.setLastName(entity.getLastName());
        try (FileWriter fileWriter = new FileWriter(file)) {
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            csvWriter.writeAll(Parser.convertToStrings(Student.class, students));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        students.remove(findById(id));
        try (FileWriter fileWriter = new FileWriter(file)) {
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            csvWriter.writeAll(Parser.convertToStrings(Student.class, students));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student findById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        throw new RuntimeException("Can't find student with id = " + id);
    }

    @Override
    public Collection<Student> findAll() throws IOException {
        try (FileReader fileReader = new FileReader(file)) {
            CSVReader csvReader = new CSVReader(fileReader);
            return Parser.convertToEntities(Student.class, csvReader.readAll());
        }
    }

}
