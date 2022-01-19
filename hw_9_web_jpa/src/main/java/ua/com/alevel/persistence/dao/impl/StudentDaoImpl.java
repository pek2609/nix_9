package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.util.QueryUtil;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Student entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Student entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        int isSuccessful = entityManager.createQuery("delete from Student s where s.id=:id")
                .setParameter("id", id)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("student modified concurrently");
        }
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(s.id) from Student s where s.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> from = criteriaQuery.from(Student.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }

        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = request.getPageSize();

        List<Student> items = entityManager.createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();

        DataTableResponse<Student> response = new DataTableResponse<>();
        response.setItems(items);

        return response;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(s.id) from Student s");
        return (Long) query.getSingleResult();
    }

    @Override
    public DataTableResponse<Student> findByGroupId(DataTableRequest request, Long groupId) {
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = page + request.getPageSize();

        List<Student> items = entityManager.createQuery(QueryUtil.getQueryStudentFindAllByGroup(request), Student.class)
                .setParameter("id", groupId)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();

        DataTableResponse<Student> response = new DataTableResponse<>();
        response.setItems(items);
        return response;
    }

    @Override
    public long countByGroupId(Long groupId) {
        Query query = entityManager.createQuery("select count (r.id) from StudyRecord r where r.group.id =:id")
                .setParameter("id", groupId);
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Student> findAll() {
        return entityManager.createQuery("select s from Student s", Student.class).getResultList();
    }
}
