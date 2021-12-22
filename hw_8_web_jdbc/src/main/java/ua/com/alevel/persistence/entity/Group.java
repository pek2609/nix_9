package ua.com.alevel.persistence.entity;

public class Group extends BaseEntity {

    private String groupName;
    private String teacherName;
    private Course course;

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

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", course=" + course +
                '}';
    }
}
