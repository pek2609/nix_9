package ua.com.alevel.facade.impl;

import ua.com.alevel.dto.student.StudentRequestDto;
import ua.com.alevel.dto.student.StudentResponseDto;
import ua.com.alevel.entity.Student;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.service.impl.StudentServiceImpl;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService = new StudentServiceImpl();

    @Override
    public void create(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        studentService.create(student);
    }

    @Override
    public void update(StudentRequestDto studentRequestDto, String id) {
        Student student = new Student();
        student.setId(id);
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        studentService.update(student);
    }

    @Override
    public void delete(String id) {
        studentService.delete(id);
    }

    @Override
    public StudentResponseDto findById(String id) {
        return new StudentResponseDto(studentService.findById(id));
    }

    @Override
    public Collection<StudentResponseDto> findAll() throws IOException {
        return studentService.findAll().stream().map(StudentResponseDto::new).collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
