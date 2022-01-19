package ua.com.alevel.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.repository.StudentRepository;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.DataTableUtil;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final CrudRepositoryHelper<Student, StudentRepository> crudRepositoryHelper;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(CrudRepositoryHelper<Student, StudentRepository> crudRepositoryHelper, StudentRepository studentRepository) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.studentRepository = studentRepository;
    }

    @Override
    public void create(Student entity) {
        crudRepositoryHelper.create(studentRepository, entity);
    }

    @Override
    public void update(Student entity) {
        crudRepositoryHelper.update(studentRepository, entity);
    }

    @Override
    public void delete(Long id) {
        crudRepositoryHelper.delete(studentRepository, id);
    }

    @Override
    public Student findById(Long id) {
        Optional<Student> student = crudRepositoryHelper.findById(studentRepository, id);
        if (student.isEmpty()) {
            throw new EntityNotFoundException("student is not found");
        }
        return student.get();
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(studentRepository, request);
    }

    @Override
    public DataTableResponse<Student> findByGroupId(DataTableRequest dataTableRequest, Long groupId) {
        Page<Student> groupPage = studentRepository.findByGroupsId(groupId, DataTableUtil.formPageableByRequest(dataTableRequest));
        return DataTableUtil.formResponse(groupPage, dataTableRequest);
    }

    @Override
    public List<Student> findAll() {
        return crudRepositoryHelper.findAll(studentRepository);
    }
}
