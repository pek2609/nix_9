package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.GroupStudentDao;
import ua.com.alevel.db.GroupStudentDB;
import ua.com.alevel.db.impl.GroupStudentDBImpl;
import ua.com.alevel.entity.GroupStudent;

import java.io.IOException;
import java.util.Collection;

public class GroupStudentDaoImpl implements GroupStudentDao {

    private final GroupStudentDB groupStudentDB = GroupStudentDBImpl.getInstance();

    @Override
    public void create(GroupStudent entity) {
        groupStudentDB.create(entity);
    }

    @Override
    public void update(GroupStudent entity) {
        groupStudentDB.create(entity);
    }

    @Override
    public void delete(String id) {
        groupStudentDB.delete(id);
    }

    @Override
    public GroupStudent findById(String id) {
        return groupStudentDB.findById(id);
    }

    @Override
    public Collection<GroupStudent> findAll() throws IOException {
        return groupStudentDB.findAll();
    }

    @Override
    public void deleteAllByGroup(String groupId) throws IOException {
        for (GroupStudent groupStudent : groupStudentDB.findAll()) {
            if (groupStudent.getGroupId().equals(groupId)) {
                groupStudentDB.delete(groupStudent.getId());
            }
        }
    }

    @Override
    public void deleteAllByStudent(String studentId) throws IOException {
        for (GroupStudent groupStudent : groupStudentDB.findAll()) {
            if (groupStudent.getStudentId().equals(studentId)) {
                groupStudentDB.delete(groupStudent.getId());
            }
        }
    }
}
