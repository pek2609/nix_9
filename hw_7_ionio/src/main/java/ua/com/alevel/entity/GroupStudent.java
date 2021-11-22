package ua.com.alevel.entity;

import java.util.Objects;

public class GroupStudent extends BaseEntity {

    private String groupId;
    private String studentId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupStudent that = (GroupStudent) o;
        return Objects.equals(groupId, that.groupId) && Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, studentId);
    }

}
