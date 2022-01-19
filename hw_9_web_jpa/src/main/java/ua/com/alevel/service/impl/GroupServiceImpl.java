package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectDataInput;
import ua.com.alevel.persistence.dao.GroupDao;
import ua.com.alevel.persistence.dao.StudyRecordDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.util.CheckCorrectData;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;
    private final StudyRecordDao recordDao;

    public GroupServiceImpl(GroupDao groupDao, StudyRecordDao recordDao) {
        this.groupDao = groupDao;
        this.recordDao = recordDao;
    }

    @Override
    public void create(Group entity) {
        checkValid(entity);
        groupDao.create(entity);
    }

    private void checkValid(Group group) {
        if (CheckCorrectData.isBlankOrNullString(group.getGroupName())) {
            throw new IncorrectDataInput("group name data is blank");
        }
        if (CheckCorrectData.isBlankOrNullString(group.getTeacherName())) {
            throw new IncorrectDataInput("teacher name data is blank");
        }
    }

    @Override
    public void update(Group entity) {
        checkValid(entity);
        if (!groupDao.existById(entity.getId())) {
            throw new EntityNotFoundException("group not found");
        }
        groupDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!groupDao.existById(id)) {
            throw new EntityNotFoundException("group not found");
        }
        recordDao.deleteByGroupId(id);
        groupDao.delete(id);
    }

    @Override
    public Group findById(Long id) {
        if (groupDao.existById(id)) {
            return groupDao.findById(id);
        }
        throw new EntityNotFoundException("group not found");
    }

    @Override
    public DataTableResponse<Group> findAll(DataTableRequest request) {
        DataTableResponse<Group> dataTableResponse = groupDao.findAll(request);
        dataTableResponse.setItemsSize(groupDao.count());
        return dataTableResponse;
    }

    @Override
    public DataTableResponse<Group> findByStudentId(DataTableRequest dataTableRequest, Long studentId) {
        DataTableResponse<Group> dataTableResponse = groupDao.findByStudentId(dataTableRequest, studentId);
        dataTableResponse.setItemsSize(groupDao.countByStudentId(studentId));
        return dataTableResponse;
    }

    @Override
    public List<Group> findAll() {
        return groupDao.findAll();
    }
}
