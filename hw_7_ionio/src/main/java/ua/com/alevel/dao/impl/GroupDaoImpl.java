package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.db.GroupDB;
import ua.com.alevel.db.impl.GroupDBImpl;
import ua.com.alevel.entity.Group;

import java.io.IOException;
import java.util.Collection;

public class GroupDaoImpl implements GroupDao {

    private final GroupDB groupDB = GroupDBImpl.getInstance();

    @Override
    public void create(Group entity) {
        groupDB.create(entity);
    }

    @Override
    public void update(Group entity) {
        groupDB.update(entity);
    }

    @Override
    public void delete(String id) {
        groupDB.delete(id);
    }

    @Override
    public Group findById(String id) {
        return groupDB.findById(id);
    }

    @Override
    public Collection<Group> findAll() throws IOException {
        return groupDB.findAll();
    }

}
