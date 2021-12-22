package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.RecordDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Record;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.util.QueryUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RecordDaoImpl implements RecordDao {

    private final JpaConfig jpaConfig;

    public RecordDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Record entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(QueryUtil.CREATE_RECORD_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setLong(3, entity.getGroup().getId());
            preparedStatement.setLong(4, entity.getStudent().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Record entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(QueryUtil.UPDATE_RECORD_QUERY + entity.getId())) {
            preparedStatement.setTimestamp(1, new Timestamp(new Date().getTime()));
            preparedStatement.setLong(2, entity.getGroup().getId());
            preparedStatement.setLong(3, entity.getStudent().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(QueryUtil.DELETE_RECORD_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.EXIST_RECORD_QUERY + id)) {
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Record findById(Long id) {
        Record record = null;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.FIND_RECORD_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                record = getStudentGroupFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return record;
    }

    @Override
    public DataTableResponse<Record> findAll(DataTableRequest request) {
        List<Record> recordList = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.getQueryRecordFindAll(request))) {
            while (resultSet.next()) {
                recordList.add(getStudentGroupFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        DataTableResponse<Record> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(recordList);
        return dataTableResponse;
    }

    @Override
    public long count() {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.COUNT_RECORD_QUERY)) {
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void deleteByGroupId(Long groupId) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(QueryUtil.DELETE_BY_GROUP_ID_QUERY + groupId)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void deleteByStudentId(Long studentId) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(QueryUtil.DELETE_BY_STUDENT_ID_QUERY + studentId)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private Record getStudentGroupFromResultSet(ResultSet resultSet) throws SQLException {
        Record record = new Record();
        record.setId(resultSet.getLong("record.id"));
        record.setCreated(resultSet.getTimestamp("record.created"));
        record.setUpdated(resultSet.getTimestamp("record.updated"));

        Group group = new Group();
        group.setId(resultSet.getLong("c.id"));
        group.setCreated(resultSet.getTimestamp("c.created"));
        group.setUpdated(resultSet.getTimestamp("c.updated"));
        group.setGroupName(resultSet.getString("group_name"));
        group.setTeacherName(resultSet.getString("teacher_name"));
        group.setCourse(Course.valueOf(resultSet.getString("course")));

        Student student = new Student();
        student.setId(resultSet.getLong("s.id"));
        student.setCreated(resultSet.getTimestamp("s.created"));
        student.setUpdated(resultSet.getTimestamp("s.updated"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        student.setAge(resultSet.getInt("age"));
        student.setPhoneNumber(resultSet.getString("phone_number"));
        student.setEmail(resultSet.getString("email"));

        record.setGroup(group);
        record.setStudent(student);

        return record;
    }
}
