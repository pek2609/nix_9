package ua.com.alevel.dto.groupstudent;

import ua.com.alevel.dto.BaseResponseDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;

public class GroupStudentResponseDto extends BaseResponseDto {

    private Group group;
    private Student student;

    public GroupStudentResponseDto(String id, Group group, Student student) {
        setId(id);
        setGroup(group);
        setStudent(student);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id='" + getId() + '\'' +
                ", group=" + group +
                ", student=" + student +
                '}';
    }

}
