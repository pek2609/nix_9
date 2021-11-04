package ua.com.alevel.db.impl;

import ua.com.alevel.MyList;
import ua.com.alevel.db.BookDB;
import ua.com.alevel.entity.Book;
import ua.com.alevel.util.GenerateIdUtil;

public final class BookDBImpl implements BookDB {

    private final MyList<Book> books;
    private static BookDBImpl instance;

    public static BookDBImpl getInstance() {
        if (instance == null) {
            instance = new BookDBImpl();
        }
        return instance;
    }

    private BookDBImpl() {
        books = new MyList<>();
    }

    @Override
    public void create(Book book) {
        book.setId(GenerateIdUtil.generateId(books));
        books.add(book);
    }

    @Override
    public void update(Book book) {
        Book cur = findById(book.getId());
        cur.setId(book.getId());
        cur.setName(book.getName());
        cur.setAuthor(book.getAuthor());
    }

    @Override
    public void delete(String id) {
        books.remove(findById(id));
    }

    @Override
    public Book findById(String id) {
        for (int i = 0; i < books.size(); i++) {
            Book cur = books.get(i);
            if (cur.getId().equals(id)) {
                return cur;
            }
        }
        throw new RuntimeException("Book with id = " + id + "is not found");
    }

    @Override
    public MyList<Book> findAll() {
        return books;
    }
}
