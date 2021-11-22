package ua.com.alevel.dto.groupstudent;

import ua.com.alevel.dto.BaseRequestDto;

public class GroupStudentRequestDto extends BaseRequestDto {

    private String groupId;
    private String studentId;

    public GroupStudentRequestDto(String groupId, String studentId) {
        setGroupId(groupId);
        setStudentId(studentId);
    }

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

}
