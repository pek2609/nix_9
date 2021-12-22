package ua.com.alevel.persistence.entity;

public class Record extends BaseEntity {

    private Group group;
    private Student student;

    public Record() {
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
