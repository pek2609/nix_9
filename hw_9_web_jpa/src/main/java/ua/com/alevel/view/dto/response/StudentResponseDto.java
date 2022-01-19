package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Student;

public class StudentResponseDto extends DtoResponse {

    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private String email;
    private String fullName;

    public StudentResponseDto(Student student) {
        super(student.getId(), student.getCreated(), student.getUpdated());
        setFirstName(student.getFirstName());
        setLastName(student.getLastName());
        setAge(student.getAge());
        setPhoneNumber(student.getPhoneNumber());
        setEmail(student.getEmail());
        setFullName(firstName + " " + lastName);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
