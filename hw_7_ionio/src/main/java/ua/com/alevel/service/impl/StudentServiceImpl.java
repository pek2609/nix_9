package ua.com.alevel.service.impl;

import ua.com.alevel.dao.GroupStudentDao;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.dao.impl.GroupStudentDaoImpl;
import ua.com.alevel.dao.impl.StudentDaoImpl;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.StudentService;

import java.io.IOException;
import java.util.Collection;

public class StudentServiceImpl implements StudentService {

    private final GroupStudentDao groupStudentDao = new GroupStudentDaoImpl();
    private final StudentDao studentDao = new StudentDaoImpl();


    @Override
    public void create(Student entity) {
        studentDao.create(entity);
    }

    @Override
    public void update(Student entity) {
        studentDao.update(entity);
    }

    @Override
    public void delete(String id) {
        try {
            Student student = studentDao.findById(id);
            groupStudentDao.deleteAllByStudent(student.getId());
            studentDao.delete(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student findById(String id) {
        return studentDao.findById(id);
    }

    @Override
    public Collection<Student> findAll() throws IOException {
        return studentDao.findAll();
    }
}
