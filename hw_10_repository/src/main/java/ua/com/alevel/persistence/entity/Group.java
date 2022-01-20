package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "classes")
public class Group extends BaseEntity {

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "teacher_name")
    private String teacherName;

    @Enumerated(EnumType.STRING)
    private Course course;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "group_student",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students;

    public Group() {
        super();
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> studyRecords) {
        this.students = studyRecords;
    }

    public void addStudent(Student student) {
//        students.add(student);
//        student.getGroups().add(this);
    }

    public void deleteStudent(Student student) {
//        students.remove(student);
//        student.getGroups().remove(this);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", course=" + course +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
