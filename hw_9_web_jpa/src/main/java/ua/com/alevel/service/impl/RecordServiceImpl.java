package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.dao.StudyRecordDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.StudyRecord;
import ua.com.alevel.service.RecordService;

@Service
public class RecordServiceImpl implements RecordService {

    private final StudyRecordDao recordDao;

    public RecordServiceImpl(StudyRecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Override
    public void create(StudyRecord entity) {
        recordDao.create(entity);
    }

    @Override
    public void update(StudyRecord entity) {
        if (!recordDao.existById(entity.getId())) {
            throw new EntityNotFoundException("record not found");
        }
        recordDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!recordDao.existById(id)) {
            throw new EntityNotFoundException("record not found");
        }
        recordDao.delete(id);
    }

    @Override
    public StudyRecord findById(Long id) {
        if (recordDao.existById(id)) {
            return recordDao.findById(id);
        }
        throw new EntityNotFoundException("record not found");
    }

    @Override
    public DataTableResponse<StudyRecord> findAll(DataTableRequest request) {
        DataTableResponse<StudyRecord> dataTableResponse = recordDao.findAll(request);
        dataTableResponse.setItemsSize(recordDao.count());
        return dataTableResponse;
    }
}
