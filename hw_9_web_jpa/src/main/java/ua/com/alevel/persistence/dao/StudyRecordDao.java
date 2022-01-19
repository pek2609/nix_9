package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.StudyRecord;

public interface StudyRecordDao extends BaseDao<StudyRecord> {

    void deleteByGroupId(Long groupId);

    void deleteByStudentId(Long studentId);
}
