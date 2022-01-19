package ua.com.alevel.util;

import ua.com.alevel.persistence.datatable.DataTableRequest;

public class QueryUtil {



    public static String getQueryGroupFindAll(DataTableRequest request) {
        if (!request.getSort().equals("studentCount")) {
            request.setSort("g." + request.getSort());
        }
        return "select g , count(r.student.id) as studentCount from Group g left outer join StudyRecord r on g.id = r.group.id group by g.id order by " +
                request.getSort() + " " +
                request.getOrder();
    }

    public static String getQueryGroupFindAllByStudent(DataTableRequest request) {
        if (!request.getSort().equals("studentCount")) {
            request.setSort("g." + request.getSort());
        }
        return "select g , count(r.student.id) as studentCount from Group g left outer join StudyRecord r on g.id = r.group.id where g" +
                " in (select g from Group g left outer join StudyRecord sr on sr.group.id = g.id where sr.student.id =:id) group by g.id" + " order by " +
                request.getSort() + " " +
                request.getOrder();
    }

    public static String getQueryStudentFindAllByGroup(DataTableRequest request) {
        return "select s from Student s join StudyRecord sr on s.id = sr.student.id where sr.group.id=:id" + " order by " +
                "s." + request.getSort() + " " +
                request.getOrder();
    }

}
