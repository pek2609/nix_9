package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.db.StudentDB;
import ua.com.alevel.db.impl.StudentDBImpl;
import ua.com.alevel.entity.Student;

import java.io.IOException;
import java.util.Collection;

public class StudentDaoImpl implements StudentDao {

    private final StudentDB studentDB = StudentDBImpl.getInstance();

    @Override
    public void create(Student entity) {
        studentDB.create(entity);
    }

    @Override
    public void update(Student entity) {
        studentDB.update(entity);
    }

    @Override
    public void delete(String id) {
        studentDB.delete(id);
    }

    @Override
    public Student findById(String id) {
        return studentDB.findById(id);
    }

    @Override
    public Collection<Student> findAll() throws IOException {
        studentDB.findAll().forEach(System.out::println);
        return studentDB.findAll();
    }
}
