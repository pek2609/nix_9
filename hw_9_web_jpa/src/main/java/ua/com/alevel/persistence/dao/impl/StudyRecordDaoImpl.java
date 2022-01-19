package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.StudyRecordDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.StudyRecord;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@Transactional
public class StudyRecordDaoImpl implements StudyRecordDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(StudyRecord entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(StudyRecord entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        int isSuccessful = entityManager.createQuery("delete from StudyRecord sr where sr.id=:id")
                .setParameter("id", id)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("study record modified concurrently");
        }
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(sr.id) from StudyRecord sr where sr.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public StudyRecord findById(Long id) {
        return entityManager.find(StudyRecord.class, id);
    }

    @Override
    public DataTableResponse<StudyRecord> findAll(DataTableRequest request) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StudyRecord> criteriaQuery = criteriaBuilder.createQuery(StudyRecord.class);
        Root<StudyRecord> from = criteriaQuery.from(StudyRecord.class);
        Join<Object, Object> group = from.join("group");
        Join<Object, Object> student = from.join("student");

        Path<Object> objectPath;
        if (request.getSort().equals("groupName")) {
            objectPath = group.get(request.getSort());
        } else {
            objectPath = student.get(request.getSort());
        }

        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(objectPath));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(objectPath));
        }

        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = request.getPageSize();

        List<StudyRecord> items = entityManager.createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();

        DataTableResponse<StudyRecord> response = new DataTableResponse<>();
        response.setItems(items);

        return response;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(sr.id) from StudyRecord sr");
        return (Long) query.getSingleResult();
    }

    @Override
    public void deleteByGroupId(Long groupId) {
        entityManager.createQuery("delete from StudyRecord sr where sr.group.id=:id")
                .setParameter("id", groupId)
                .executeUpdate();
    }

    @Override
    public void deleteByStudentId(Long studentId) {
        entityManager.createQuery("delete from StudyRecord sr where sr.student.id=:id")
                .setParameter("id", studentId)
                .executeUpdate();
    }
}
