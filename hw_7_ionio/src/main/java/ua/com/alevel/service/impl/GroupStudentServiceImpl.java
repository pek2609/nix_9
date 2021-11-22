package ua.com.alevel.service.impl;

import ua.com.alevel.dao.GroupStudentDao;
import ua.com.alevel.dao.impl.GroupStudentDaoImpl;
import ua.com.alevel.entity.GroupStudent;
import ua.com.alevel.service.GroupStudentService;

import java.io.IOException;
import java.util.Collection;

public class GroupStudentServiceImpl implements GroupStudentService {

    private final GroupStudentDao groupStudentDao = new GroupStudentDaoImpl();

    @Override
    public void create(GroupStudent entity) {
        groupStudentDao.create(entity);
    }

    @Override
    public void update(GroupStudent entity) {
        groupStudentDao.update(entity);
    }

    @Override
    public void delete(String id) {
        groupStudentDao.delete(id);
    }

    @Override
    public GroupStudent findById(String id) {
        return groupStudentDao.findById(id);
    }

    @Override
    public Collection<GroupStudent> findAll() throws IOException {
        return groupStudentDao.findAll();
    }

}
