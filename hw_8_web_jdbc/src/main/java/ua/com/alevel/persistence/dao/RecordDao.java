package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Record;

public interface RecordDao extends BaseDao<Record> {

    void deleteByGroupId(Long groupId);

    void deleteByStudentId(Long studentId);
}
