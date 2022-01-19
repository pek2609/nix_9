package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.GroupDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.util.QueryUtil;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class GroupDaoImpl implements GroupDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Group entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Group entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        int isSuccessful = entityManager.createQuery("delete from Group g where g.id=:id")
                .setParameter("id", id)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("group modified concurrently");
        }
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(g.id) from Group g where g.id = :id").setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Group findById(Long id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public DataTableResponse<Group> findAll(DataTableRequest request) {
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = request.getPageSize();

        List<Object[]> resultList = entityManager.createQuery(QueryUtil.getQueryGroupFindAll(request), Object[].class)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();

        List<Group> groupList = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        for (Object[] objects : resultList) {
            groupList.add((Group) objects[0]);
            otherParamMap.put(((Group) objects[0]).getId(), objects[1]);
        }

        DataTableResponse<Group> response = new DataTableResponse<>();
        response.setItems(groupList);
        response.setOtherParamMap(otherParamMap);

        return response;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(g.id) from Group g");
        return (Long) query.getSingleResult();
    }

    @Override
    public DataTableResponse<Group> findByStudentId(DataTableRequest request, Long studentId) {
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        List<Object[]> res = entityManager.createQuery(QueryUtil.getQueryGroupFindAllByStudent(request), Object[].class)
                .setParameter("id", studentId)
                .setFirstResult(page)
                .setMaxResults(request.getPageSize())
                .getResultList();

        List<Group> groupList = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        for (Object[] objects : res) {
            groupList.add((Group) objects[0]);
            otherParamMap.put(((Group) objects[0]).getId(), objects[1]);
        }

        DataTableResponse<Group> response = new DataTableResponse<>();
        response.setItems(groupList);
        response.setOtherParamMap(otherParamMap);

        return response;
    }

    @Override
    public long countByStudentId(Long studentId) {
        Query query = entityManager.createQuery("select count (r.id) from StudyRecord r where r.student.id=:id")
                .setParameter("id", studentId);
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Group> findAll() {
        return entityManager.createQuery("select g from Group g", Group.class).getResultList();
    }

}
