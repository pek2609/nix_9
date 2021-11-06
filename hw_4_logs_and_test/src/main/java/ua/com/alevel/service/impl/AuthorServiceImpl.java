package ua.com.alevel.service.impl;

import ua.com.alevel.MyList;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.dao.impl.AuthorDaoImpl;
import ua.com.alevel.dao.impl.BookDaoImpl;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.util.CheckExistEntityUtil;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao = new AuthorDaoImpl();
    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public void create(Author author) {
        if (CheckExistEntityUtil.isExist(author, this)) {
            throw new RuntimeException("Author " + author.getFirstName() + " " + author.getLastName() + " is already exist!");
        }
        if (author.isBlank()) {
            throw new RuntimeException("Author " + author.getFirstName() + " " + author.getLastName() + " has blank name or surname!");
        }
        authorDao.create(author);
    }

    @Override
    public void update(Author author) {
        if (author.isBlank()) {
            throw new RuntimeException("Author " + author.getFirstName() + " " + author.getLastName() + " has blank name or surname!");
        }
        authorDao.update(author);
    }

    @Override
    public void delete(String id) {
        authorDao.delete(id);
        deleteBooksForAuthor(id);
    }

    @Override
    public Author findById(String id) {
        return authorDao.findById(id);
    }

    @Override
    public MyList<Author> findAll() {
        return authorDao.findAll();
    }

    private void deleteBooksForAuthor(String idAuthor) {
        MyList<Book> books = bookDao.findAll();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getAuthor().getId().equals(idAuthor)) {
                bookDao.delete(books.get(i).getId());
            }
        }
    }
}

