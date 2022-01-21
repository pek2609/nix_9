package ua.com.alevel.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectDataInput;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.repository.GroupRepository;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.CheckCorrectData;
import ua.com.alevel.util.DataTableUtil;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class GroupServiceImpl implements GroupService {

    private final CrudRepositoryHelper<Group, GroupRepository> crudRepositoryHelper;
    private final GroupRepository repository;
    private final StudentService studentService;

    public GroupServiceImpl(CrudRepositoryHelper<Group, GroupRepository> crudRepositoryHelper, GroupRepository repository, StudentService studentService) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.repository = repository;
        this.studentService = studentService;
    }

    private void checkValid(Group group) {
        if (CheckCorrectData.isBlankOrNullString(group.getGroupName())) {
            throw new IncorrectDataInput("group name data is blank");
        }
        if (CheckCorrectData.isBlankOrNullString(group.getTeacherName())) {
            throw new IncorrectDataInput("teacher name data is blank");
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void create(Group entity) {
        checkValid(entity);
        crudRepositoryHelper.create(repository, entity);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void update(Group entity) {
        checkValid(entity);
        crudRepositoryHelper.update(repository, entity);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void delete(Long id) {
        crudRepositoryHelper.delete(repository, id);
    }

    @Transactional(readOnly = true)
    @Override
    public Group findById(Long id) {
        Optional<Group> group = crudRepositoryHelper.findById(repository, id);
        if (group.isEmpty()) {
            throw new EntityNotFoundException("group is not found");
        }
        return group.get();
    }

    @Transactional(readOnly = true)
    @Override
    public DataTableResponse<Group> findAll(DataTableRequest dataTableRequest) {
        DataTableResponse<Group> all = DataTableUtil.formResponse(repository.findAllGroupsWithCount(DataTableUtil.formPageableByRequest(dataTableRequest)), dataTableRequest);
        DataTableUtil.setOtherParamMapStudCount(all);
        return all;
    }

    @Transactional(readOnly = true)
    @Override
    public DataTableResponse<Group> findByStudentId(DataTableRequest dataTableRequest, Long studentId) {
        Page<Group> groupPage = repository.findByStudentsId(studentId, DataTableUtil.formPageableByRequest(dataTableRequest));
        DataTableResponse<Group> all = DataTableUtil.formResponse(groupPage, dataTableRequest);
        DataTableUtil.setOtherParamMapStudCount(all);
        return all;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Group> findAll() {
        return crudRepositoryHelper.findAll(repository);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void addStudent(Long groupId, Long studentId) {
        Group group = findById(groupId);
        Student student = studentService.findById(studentId);
        group.addStudent(student);
        crudRepositoryHelper.update(repository, group);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void removeStudent(Long groupId, Long studentId) {
        Group group = findById(groupId);
        Student student = studentService.findById(studentId);
        group.deleteStudent(student);
        crudRepositoryHelper.update(repository, group);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Group> findByStudentId(Long studentId) {
        return repository.findByStudentsId(studentId);
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Group> findNotByStudentsId(Long studentId) {
        return repository.findByStudentsIdNot(studentId);
    }
}
