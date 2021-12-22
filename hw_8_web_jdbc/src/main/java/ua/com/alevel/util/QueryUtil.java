package ua.com.alevel.util;

import ua.com.alevel.persistence.datatable.DataTableRequest;

public final class QueryUtil {

    private QueryUtil() {
    }

    public static final String CREATE_STUDENT_QUERY = "INSERT INTO students VALUES(default,?,?,?,?,?,?,?)";
    public static final String UPDATE_STUDENT_QUERY = "UPDATE students SET updated = ?, first_name = ?, last_name = ?, age = ?, phone_number = ?, email = ? WHERE id =";
    public static final String DELETE_STUDENT_QUERY = "DELETE FROM students WHERE id =";
    public static final String EXIST_STUDENT_QUERY = "SELECT COUNT(*) as count FROM students WHERE id =";
    public static final String COUNT_STUDENT_QUERY = "SELECT COUNT(*) as count FROM students";
    public static final String FIND_STUDENT_BY_ID_QUERY = "SELECT * FROM students WHERE id =";
    public static final String COUNT_STUDENT_BY_GROUP_QUERY = "SELECT COUNT(*) as count FROM record where group_id =";
    public static final String FIND_ALL_STUDENTS_QUERY = "SELECT * FROM students";

    public static final String FIND_ALL_GROUPS_QUERY = "SELECT * FROM classes";
    public static final String CREATE_GROUP_QUERY = "INSERT INTO classes VALUES(default,?,?,?,?,?)";
    public static final String UPDATE_GROUP_QUERY = "UPDATE classes SET updated = ?, group_name = ?, teacher_name = ?, course = ? WHERE id =";
    public static final String DELETE_GROUP_QUERY = "DELETE FROM classes WHERE id =";
    public static final String EXIST_GROUP_QUERY = "SELECT COUNT(*) as count FROM classes WHERE id =";
    public static final String FIND_GROUP_BY_ID_QUERY = "SELECT * FROM classes WHERE id =";
    public static final String COUNT_GROUP_QUERY = "SELECT COUNT(*) as count FROM classes";
    public static final String COUNT_GROUP_BY_STUDENT_QUERY = "SELECT COUNT(*) as count FROM record where student_id =";


    public static final String CREATE_RECORD_QUERY = "INSERT INTO record VALUES(default,?,?,?,?)";
    public static final String UPDATE_RECORD_QUERY = "UPDATE record SET updated = ?, group_id = ?, student_id = ? WHERE id =";
    public static final String DELETE_RECORD_QUERY = "DELETE FROM record WHERE id =";
    public static final String EXIST_RECORD_QUERY = "SELECT COUNT(*) AS count FROM record WHERE id =";
    public static final String FIND_RECORD_BY_ID_QUERY = "select * from record join classes c on record.group_id = c.id JOIN students s on record.student_id = s.id WHERE record.id=";
    public static final String DELETE_BY_GROUP_ID_QUERY = "DELETE FROM record WHERE group_id=";
    public static final String DELETE_BY_STUDENT_ID_QUERY = "DELETE FROM record WHERE student_id=";
    public static final String COUNT_RECORD_QUERY = "SELECT COUNT(*) AS count FROM record";

    public static String getQueryFindAll(String tableName, DataTableRequest request) {
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        return "select * from " + tableName + " order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();
    }

    public static String getQueryGroupFindAll(DataTableRequest request) {
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        return "select classes.id as id, classes.created as created, classes.updated as updated, group_name" +
                ", teacher_name, course, count(student_id) as student_count from classes left outer join record r " +
                "on classes.id = r.group_id group by classes.id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();
    }

    public static String getQueryStudentFindAllByGroup(DataTableRequest request, Long groupId) {
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        return "select students.id as id, students.created as created, students.updated as updated, first_name, " +
                "last_name, age, phone_number, email " +
                "from students " +
                "join record r on students.id = r.student_id where group_id =" + groupId + " order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();
    }

    public static String getQueryGroupFindAllByStudent(DataTableRequest request, Long studentId) {
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        return "select classes.id, classes.created, classes.updated, group_name, teacher_name, course, student_count from " +
                "(select classes.id, classes.created, classes.updated, group_name, teacher_name, course " +
                "from classes left outer join record r on classes.id = r.group_id where student_id =" + studentId + ") classes join " +
                "(select classes.id, count(student_id) as student_count from classes left outer join record r on classes.id = r.group_id group by classes.id) counts on classes.id = counts.id " + " order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();
    }

    public static String getQueryRecordFindAll(DataTableRequest request) {
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        return "select * from record " +
                "join classes as c on record.group_id = c.id " +
                "join students as s on record.student_id = s.id " +
                "order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();
    }
}