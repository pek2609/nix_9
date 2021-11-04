package ua.com.alevel.dao.impl;

import ua.com.alevel.MyList;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.db.BookDB;
import ua.com.alevel.db.impl.BookDBImpl;
import ua.com.alevel.entity.Book;

public class BookDaoImpl implements BookDao {

    private final BookDB bookDB = BookDBImpl.getInstance();

    @Override
    public void create(Book book) {
        bookDB.create(book);
    }

    @Override
    public void update(Book book) {
        bookDB.update(book);
    }

    @Override
    public void delete(String id) {
        bookDB.delete(id);
    }

    @Override
    public Book findById(String id) {
        return bookDB.findById(id);
    }

    @Override
    public MyList<Book> findAll() {
        return bookDB.findAll();
    }
}
