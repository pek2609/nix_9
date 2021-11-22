package ua.com.alevel.service.impl;

import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.dao.GroupStudentDao;
import ua.com.alevel.dao.impl.GroupDaoImpl;
import ua.com.alevel.dao.impl.GroupStudentDaoImpl;
import ua.com.alevel.entity.Group;
import ua.com.alevel.service.GroupService;

import java.io.IOException;
import java.util.Collection;

public class GroupServiceImpl implements GroupService {

    private final GroupStudentDao groupStudentDao = new GroupStudentDaoImpl();
    private final GroupDao groupDao = new GroupDaoImpl();

    @Override
    public void create(Group entity) {
        groupDao.create(entity);
    }

    @Override
    public void update(Group entity) {
        groupDao.update(entity);
    }

    @Override
    public void delete(String id) {
        try {
            Group group = groupDao.findById(id);
            groupStudentDao.deleteAllByGroup(group.getId());
            groupDao.delete(id);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Group findById(String id) {
        return groupDao.findById(id);
    }

    @Override
    public Collection<Group> findAll() throws IOException {
        return groupDao.findAll();
    }

}
