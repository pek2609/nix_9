package ua.com.alevel.entity;

import java.util.Objects;

public class Group extends BaseEntity {

    private String name;
    private String teacherName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name) && Objects.equals(teacherName, group.teacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, teacherName);
    }

}
