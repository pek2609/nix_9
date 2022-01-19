package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.entity.StudyRecord;

public class RecordResponseDto extends DtoResponse {

    private Group group;
    private Student student;
    private Long groupId;
    private Long studentId;

    public RecordResponseDto(StudyRecord record) {
        super(record.getId(), record.getCreated(), record.getUpdated());
        setGroup(record.getGroup());
        setStudent(record.getStudent());
        setGroupId(record.getGroup().getId());
        setStudentId(record.getStudent().getId());
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "RecordResponseDto{" +
                "group=" + group +
                ", student=" + student +
                ", groupId=" + groupId +
                ", studentId=" + studentId +
                '}';
    }
}
