package ua.com.alevel.db.impl;

import ua.com.alevel.CSVReader;
import ua.com.alevel.CSVWriter;
import ua.com.alevel.db.GroupStudentDB;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.GroupStudent;
import ua.com.alevel.entity.Student;
import ua.com.alevel.util.GenerateIdUtil;
import ua.com.alevel.util.Parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;

public class GroupStudentDBImpl implements GroupStudentDB {

    private static final String PATH_CSV = "hw_7_ionio/group_student.csv";
    private final Collection<GroupStudent> groupStudents;
    private static GroupStudentDBImpl instance;

    public static GroupStudentDBImpl getInstance() {
        if (instance == null) {
            instance = new GroupStudentDBImpl();
        }
        return instance;
    }

    private GroupStudentDBImpl() {
        groupStudents = new LinkedHashSet<>();
    }

    @Override
    public void create(GroupStudent entity) {
        entity.setId(GenerateIdUtil.generateId(groupStudents));
        groupStudents.add(entity);
        try (FileWriter fileWriter = new FileWriter(PATH_CSV)) {
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            csvWriter.writeAll(Parser.convertToStrings(GroupStudent.class, groupStudents));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GroupStudent entity) {
        GroupStudent byId = findById(entity.getId());
        byId.setGroupId(entity.getGroupId());
        byId.setStudentId(entity.getStudentId());
        try (FileWriter fileWriter = new FileWriter(PATH_CSV)) {
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            csvWriter.writeAll(Parser.convertToStrings(GroupStudent.class, groupStudents));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        groupStudents.remove(findById(id));
        try (FileWriter fileWriter = new FileWriter(PATH_CSV)) {
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            csvWriter.writeAll(Parser.convertToStrings(GroupStudent.class, groupStudents));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GroupStudent findById(String id) {
        for (GroupStudent student : groupStudents) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        throw new RuntimeException("Can't find student with id = " + id);
    }

    @Override
    public Collection<GroupStudent> findAll() throws IOException {
        try (FileReader fileReader = new FileReader(PATH_CSV)) {
            CSVReader csvReader = new CSVReader(fileReader);
            return Parser.convertToEntities(GroupStudent.class, csvReader.readAll());
        }
    }

}
