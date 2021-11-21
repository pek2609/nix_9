package ua.com.alevel.db.impl;


import ua.com.alevel.CSVReader;
import ua.com.alevel.CSVWriter;
import ua.com.alevel.db.GroupDB;
import ua.com.alevel.entity.Group;
import ua.com.alevel.util.GenerateIdUtil;
import ua.com.alevel.util.Parser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;

public class GroupDBImpl implements GroupDB {

    private static final String PATH_CSV = "hw_7_ionio/groups.csv";
    private Collection<Group> groups;
    private static GroupDBImpl instance;

    public static GroupDBImpl getInstance() {
        if (instance == null) {
            instance = new GroupDBImpl();
        }
        return instance;
    }

    private GroupDBImpl() {
        try {
            groups = findAll();
        } catch (IOException e) {
            groups = new LinkedHashSet<>();
        }
    }

    @Override
    public void create(Group entity) {
        entity.setId(GenerateIdUtil.generateId(groups));
        groups.add(entity);
        try (FileWriter fileWriter = new FileWriter(PATH_CSV)) {
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            csvWriter.writeAll(Parser.convertToStrings(Group.class, groups));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Group entity) {
        Group byId = findById(entity.getId());
        byId.setName(entity.getName());
        byId.setTeacherName(entity.getTeacherName());
        try (FileWriter fileWriter = new FileWriter(PATH_CSV)) {
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            csvWriter.writeAll(Parser.convertToStrings(Group.class, groups));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        groups.remove(findById(id));
        try (FileWriter fileWriter = new FileWriter(PATH_CSV)) {
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            csvWriter.writeAll(Parser.convertToStrings(Group.class, groups));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Group findById(String id) {
        for (Group group : groups) {
            if (group.getId().equals(id)) {
                return group;
            }
        }
        throw new RuntimeException("Can't find group with id = " + id);
    }

    @Override
    public Collection<Group> findAll() throws IOException {
        try (FileReader fileReader = new FileReader(PATH_CSV)) {
            CSVReader csvReader = new CSVReader(fileReader);
            return Parser.convertToEntities(Group.class, csvReader.readAll());
        }
    }
}

