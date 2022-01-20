package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.persistence.entity.Group;

public class GroupResponseDto extends DtoResponse {

    private String groupName;
    private String teacherName;
    private Course course;
    private int studentCount;

    public GroupResponseDto(Group group) {
        super(group.getId(), group.getCreated(), group.getUpdated());
        setGroupName(group.getGroupName());
        setTeacherName(group.getTeacherName());
        setCourse(group.getCourse());
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
}
