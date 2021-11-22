package ua.com.alevel.dao;

import ua.com.alevel.entity.GroupStudent;

import java.io.IOException;

public interface GroupStudentDao extends BaseDao<GroupStudent> {

    void deleteAllByGroup(String groupId) throws IOException;

    void deleteAllByStudent(String studentId) throws IOException;

}
