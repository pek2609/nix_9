package ua.com.alevel.dto.group;

import ua.com.alevel.dto.BaseResponseDto;
import ua.com.alevel.entity.Group;

public class GroupResponseDto extends BaseResponseDto {

    private String name;
    private String teacherName;

    public GroupResponseDto(Group group) {
        setId(group.getId());
        setName(group.getName());
        setTeacherName(group.getTeacherName());
    }

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
        return "GroupResponseDto{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
