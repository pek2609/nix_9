package ua.com.alevel.dao;

import ua.com.alevel.MyList;
import ua.com.alevel.entity.BaseEntity;

public interface BaseDao<ENTITY extends BaseEntity> {

    void create(ENTITY entity);

    void update(ENTITY entity);

    void delete(String id);

    ENTITY findById(String id);

    MyList<ENTITY> findAll();
}
