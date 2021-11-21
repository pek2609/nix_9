package ua.com.alevel.dto.group;

import ua.com.alevel.dto.BaseRequestDto;

public class GroupRequestDto extends BaseRequestDto {

    private String name;
    private String teacherName;

    public GroupRequestDto(String name, String teacherName) {
        setName(name);
        setTeacherName(teacherName);
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
}
