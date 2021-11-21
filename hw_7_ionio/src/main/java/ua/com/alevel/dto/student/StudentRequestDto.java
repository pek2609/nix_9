package ua.com.alevel.dto.student;

import ua.com.alevel.dto.BaseRequestDto;

public class StudentRequestDto extends BaseRequestDto {

    private String firstName;
    private String lastName;

    public StudentRequestDto(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
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
}
