package ua.com.alevel.db;

import ua.com.alevel.MyList;
import ua.com.alevel.entity.BaseEntity;

public interface BaseDB<ENTITY extends BaseEntity> {

    void create(ENTITY entity);

    void update(ENTITY entity);

    void delete(String id);

    ENTITY findById(String id);

    MyList<ENTITY> findAll();
}
