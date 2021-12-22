package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
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
public class StudentDaoImpl implements StudentDao {

    private final JpaConfig jpaConfig;

    public StudentDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Student entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(QueryUtil.CREATE_STUDENT_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setString(3, entity.getFirstName());
            preparedStatement.setString(4, entity.getLastName());
            preparedStatement.setInt(5, entity.getAge());
            preparedStatement.setString(6, entity.getPhoneNumber());
            preparedStatement.setString(7, entity.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Student entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(QueryUtil.UPDATE_STUDENT_QUERY + entity.getId())) {
            preparedStatement.setString(2, entity.getFirstName());
            preparedStatement.setTimestamp(1, new Timestamp(new Date().getTime()));
            preparedStatement.setString(3, entity.getLastName());
            preparedStatement.setInt(4, entity.getAge());
            preparedStatement.setString(5, entity.getPhoneNumber());
            preparedStatement.setString(6, entity.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(QueryUtil.DELETE_STUDENT_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.EXIST_STUDENT_QUERY + id)) {
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Student findById(Long id) {
        Student student = null;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.FIND_STUDENT_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                student = getStudentFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return student;
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        System.out.println(request);
        List<Student> studentList = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.getQueryFindAll("students", request))) {
            System.out.println(QueryUtil.getQueryFindAll("students", request));
            while (resultSet.next()) {
                studentList.add(getStudentFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        DataTableResponse<Student> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(studentList);
        return dataTableResponse;
    }

    @Override
    public long count() {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.COUNT_STUDENT_QUERY)) {
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public DataTableResponse<Student> findByGroupId(DataTableRequest dataTableRequest, Long groupId) {
        List<Student> recordList = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.getQueryStudentFindAllByGroup(dataTableRequest, groupId))) {
            while (resultSet.next()) {
                recordList.add(getStudentFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        DataTableResponse<Student> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(recordList);
        return dataTableResponse;
    }

    @Override
    public long countByGroupId(Long groupId) {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.COUNT_STUDENT_BY_GROUP_QUERY + groupId)) {
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(QueryUtil.FIND_ALL_STUDENTS_QUERY)) {
            while (resultSet.next()) {
                Student student = getStudentFromResultSet(resultSet);
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    private Student getStudentFromResultSet(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setCreated(resultSet.getTimestamp("created"));
        student.setUpdated(resultSet.getTimestamp("updated"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        student.setAge(resultSet.getInt("age"));
        student.setPhoneNumber(resultSet.getString("phone_number"));
        student.setEmail(resultSet.getString("email"));
        return student;
    }
}
