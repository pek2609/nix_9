package ua.com.alevel.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "study_records", uniqueConstraints = {@UniqueConstraint(columnNames = {"group_id", "student_id"})})
public class StudyRecord  extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public StudyRecord() {
        super();
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
}
