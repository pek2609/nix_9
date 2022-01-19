package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.Date;
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

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<StudyRecord> studyRecords;

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

    public Set<StudyRecord> getStudyRecords() {
        return studyRecords;
    }

    public void setStudyRecords(Set<StudyRecord> studyRecords) {
        this.studyRecords = studyRecords;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", course=" + course +
                '}';
    }
}
