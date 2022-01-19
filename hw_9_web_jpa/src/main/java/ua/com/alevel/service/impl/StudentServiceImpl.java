package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectDataInput;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.dao.StudyRecordDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.CheckCorrectData;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;
    private final StudyRecordDao recordDao;

    public StudentServiceImpl(StudentDao studentDao, StudyRecordDao recordDao) {
        this.studentDao = studentDao;
        this.recordDao = recordDao;
    }

    @Override
    public void create(Student entity) {
        checkValid(entity);
        studentDao.create(entity);
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

    @Override
    public void update(Student entity) {
        checkValid(entity);
        if (!studentDao.existById(entity.getId())) {
            throw new EntityNotFoundException("student not found");
        }
        studentDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!studentDao.existById(id)) {
            throw new EntityNotFoundException("student not found");
        }
        recordDao.deleteByStudentId(id);
        studentDao.delete(id);
    }

    @Override
    public Student findById(Long id) {
        if (studentDao.existById(id)) {
            return studentDao.findById(id);
        }
        throw new EntityNotFoundException("student not found");
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        DataTableResponse<Student> dataTableResponse = studentDao.findAll(request);
        dataTableResponse.setItemsSize(studentDao.count());
        return dataTableResponse;
    }

    @Override
    public DataTableResponse<Student> findByGroupId(DataTableRequest dataTableRequest, Long groupId) {
        DataTableResponse<Student> dataTableResponse = studentDao.findByGroupId(dataTableRequest, groupId);
        dataTableResponse.setItemsSize(studentDao.countByGroupId(groupId));
        return dataTableResponse;
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }
}
