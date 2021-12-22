package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.GroupDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.util.QueryUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class GroupDaoImpl implements GroupDao {

    private final JpaConfig jpaConfig;

    public GroupDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Group entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(QueryUtil.CREATE_GROUP_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setString(3, entity.getGroupName());
            preparedStatement.setString(4, entity.getTeacherName());
            preparedStatement.setString(5, entity.getCourse().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Group entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(QueryUtil.UPDATE_GROUP_QUERY + entity.getId())) {
            preparedStatement.setTimestamp(1, new Timestamp(new Date().getTime()));
            preparedStatement.setString(2, entity.getGroupName());
            preparedStatement.setString(3, entity.getTeacherName());
            preparedStatement.setString(4, entity.getCourse().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(QueryUtil.DELETE_GROUP_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.EXIST_GROUP_QUERY + id)) {
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Group findById(Long id) {
        Group group = null;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.FIND_GROUP_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                group = getGroupFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return group;
    }

    @Override
    public DataTableResponse<Group> findAll(DataTableRequest request) {
        List<Group> groupList = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.getQueryGroupFindAll(request))) {
            while (resultSet.next()) {
                GroupResultSet groupResultSet = convertResultSetToGroupResultSet(resultSet);
                groupList.add(groupResultSet.getGroup());
                otherParamMap.put(groupResultSet.getGroup().getId(), groupResultSet.getStudentCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataTableResponse<Group> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(groupList);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    @Override
    public long count() {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.COUNT_GROUP_QUERY)) {
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Group> findAll() {
        List<Group> groupList = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.FIND_ALL_GROUPS_QUERY)) {
            while (resultSet.next()) {
                groupList.add(getGroupFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupList;
    }

    @Override
    public DataTableResponse<Group> findByStudentId(DataTableRequest dataTableRequest, Long studentId) {
        List<Group> groupList = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.getQueryGroupFindAllByStudent(dataTableRequest, studentId))) {
            while (resultSet.next()) {
                GroupResultSet groupResultSet = convertResultSetToGroupResultSet(resultSet);
                groupList.add(groupResultSet.getGroup());
                otherParamMap.put(groupResultSet.getGroup().getId(), groupResultSet.getStudentCount());
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        DataTableResponse<Group> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(groupList);
        dataTableResponse.setOtherParamMap(otherParamMap);
        return dataTableResponse;
    }

    @Override
    public long countByStudentId(Long studentId) {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.COUNT_GROUP_BY_STUDENT_QUERY + studentId)) {
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    private Group getGroupFromResultSet(ResultSet resultSet) throws SQLException {
        Group group = new Group();
        group.setId(resultSet.getLong("id"));
        group.setCreated(resultSet.getTimestamp("created"));
        group.setUpdated(resultSet.getTimestamp("updated"));
        group.setGroupName(resultSet.getString("group_name"));
        group.setTeacherName(resultSet.getString("teacher_name"));
        group.setCourse(Course.valueOf(resultSet.getString("course")));
        return group;
    }

    private GroupResultSet convertResultSetToGroupResultSet(ResultSet resultSet) throws SQLException {
        Group group = getGroupFromResultSet(resultSet);
        long studentCount = resultSet.getInt("student_count");
        return new GroupResultSet(group, studentCount);
    }

    private record GroupResultSet(Group group, long studentCount) {

        public Group getGroup() {
            return group;
        }

        public long getStudentCount() {
            return studentCount;
        }
    }
}
