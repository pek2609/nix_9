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
import ua.com.alevel.persistence.repository.StudentRepository;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.CheckCorrectData;
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

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void create(Student entity) {
        checkValid(entity);
        crudRepositoryHelper.create(studentRepository, entity);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void update(Student entity) {
        checkValid(entity);
        crudRepositoryHelper.update(studentRepository, entity);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void delete(Long id) {
        crudRepositoryHelper.delete(studentRepository, id);
    }

    @Transactional(readOnly = true)
    @Override
    public Student findById(Long id) {
        Optional<Student> student = crudRepositoryHelper.findById(studentRepository, id);
        if (student.isEmpty()) {
            throw new EntityNotFoundException("student is not found");
        }
        return student.get();
    }

    @Transactional(readOnly = true)
    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(studentRepository, request);
    }

    @Transactional(readOnly = true)
    @Override
    public DataTableResponse<Student> findByGroupId(DataTableRequest dataTableRequest, Long groupId) {
        Page<Student> groupPage = studentRepository.findByGroupsId(groupId, DataTableUtil.formPageableByRequest(dataTableRequest));
        return DataTableUtil.formResponse(groupPage, dataTableRequest);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> findAll() {
        return crudRepositoryHelper.findAll(studentRepository);
    }

    private void checkValid(Student student) {
        if (CheckCorrectData.isBlankOrNullString(student.getFirstName())) {
            throw new IncorrectDataInput("firstname is blank");
        }
        if (CheckCorrectData.isBlankOrNullString(student.getLastName())) {
            throw new IncorrectDataInput("lastname is blank");
        }
        if (CheckCorrectData.isBlankOrNullString(student.getPhoneNumber())) {
            throw new IncorrectDataInput("phone number is blank");
        }
        if (CheckCorrectData.isBlankOrNullString(student.getEmail())) {
            throw new IncorrectDataInput("email is blank");
        }
        if (!CheckCorrectData.isCorrectNumber(student.getAge(), 14, 99)) {
            throw new IncorrectDataInput("age must be in interval data [14;99]");
        }
        if (CheckCorrectData.checkPhoneNumber(student.getPhoneNumber())) {
            throw new IncorrectDataInput("uncorrected phone number input (Example: +3806612345678 or 06612345678");
        }
        if (CheckCorrectData.checkEmail(student.getEmail())) {
            throw new IncorrectDataInput("uncorrected email input (Example: your.email@gmail.com");
        }
    }
}
