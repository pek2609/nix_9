package ua.com.alevel.dao.impl;

import ua.com.alevel.MyUniqueList;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.db.AuthorDB;
import ua.com.alevel.db.impl.AuthorDBImpl;
import ua.com.alevel.entity.Author;

public class AuthorDaoImpl implements AuthorDao {

    private final AuthorDB authorDB = AuthorDBImpl.getInstance();

    @Override
    public void create(Author author) {
        authorDB.create(author);
    }

    @Override
    public void update(Author author) {
        authorDB.update(author);
    }

    @Override
    public void delete(String id) {
        authorDB.delete(id);
    }

    @Override
    public Author findById(String id) {
        return authorDB.findById(id);
    }

    @Override
    public MyUniqueList<Author> findAll() {
        return authorDB.findAll();
    }
}
