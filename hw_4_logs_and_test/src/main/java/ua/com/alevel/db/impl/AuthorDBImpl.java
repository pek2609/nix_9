package ua.com.alevel.db.impl;

import ua.com.alevel.MyUniqueList;
import ua.com.alevel.db.AuthorDB;
import ua.com.alevel.entity.Author;
import ua.com.alevel.util.GenerateIdUtil;

public final class AuthorDBImpl implements AuthorDB {

    private final MyUniqueList<Author> authors;
    private static AuthorDBImpl instance;

    public static AuthorDBImpl getInstance() {
        if (instance == null) {
            instance = new AuthorDBImpl();
        }
        return instance;
    }

    private AuthorDBImpl() {
        authors = new MyUniqueList<>();
    }

    @Override
    public void create(Author author) {
        author.setId(GenerateIdUtil.generateId(authors));
        authors.add(author);
    }

    @Override
    public void update(Author author) {
        Author cur = findById(author.getId());
        cur.setFirstName(author.getFirstName());
        cur.setLastName(author.getLastName());
    }

    @Override
    public void delete(String id) {
        authors.remove(findById(id));
    }

    @Override
    public Author findById(String id) {
        for (Author author : authors) {
            if (author.getId().equals(id)) {
                return author;
            }
        }
        throw new RuntimeException("Author with id = " + id + " is not found");
    }

    @Override
    public MyUniqueList<Author> findAll() {
        return authors;
    }
}
