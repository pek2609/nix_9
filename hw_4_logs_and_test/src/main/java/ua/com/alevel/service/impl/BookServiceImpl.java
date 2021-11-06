package ua.com.alevel.service.impl;

import ua.com.alevel.MyList;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.dao.impl.BookDaoImpl;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.BookService;
import ua.com.alevel.util.CheckExistEntityUtil;

public class BookServiceImpl implements BookService {

    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public void create(Book book) {
        if (CheckExistEntityUtil.isExist(book, this)) {
            throw new RuntimeException("Book " + book.getName() + " with Author " + book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName() + " is already exist!");
        }
        bookDao.create(book);
    }

    @Override
    public void update(Book book) {
        bookDao.update(book);
    }

    @Override
    public void delete(String id) {
        bookDao.delete(id);
    }

    @Override
    public Book findById(String id) {
        return bookDao.findById(id);
    }

    @Override
    public MyList<Book> findAll() {
        return bookDao.findAll();
    }
}
