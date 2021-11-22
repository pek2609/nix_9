package ua.com.alevel.dto.student;

import ua.com.alevel.dto.BaseResponseDto;
import ua.com.alevel.entity.Student;

public class StudentResponseDto extends BaseResponseDto {

    private String firstName;
    private String lastName;

    public StudentResponseDto(Student student) {
        setId(student.getId());
        setFirstName(student.getFirstName());
        setLastName(student.getLastName());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + getId() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
