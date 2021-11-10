package ua.com.alevel.db.impl;

import ua.com.alevel.MyUniqueList;
import ua.com.alevel.db.BookDB;
import ua.com.alevel.entity.Book;
import ua.com.alevel.util.GenerateIdUtil;

public final class BookDBImpl implements BookDB {

    private final MyUniqueList<Book> books;
    private static BookDBImpl instance;

    public static BookDBImpl getInstance() {
        if (instance == null) {
            instance = new BookDBImpl();
        }
        return instance;
    }

    private BookDBImpl() {
        books = new MyUniqueList<>();
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
        cur.setAuthors(book.getAuthors());
    }

    @Override
    public void delete(String id) {
        books.remove(findById(id));
    }

    @Override
    public Book findById(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        throw new RuntimeException("Book with id = " + id + "is not found");
    }

    @Override
    public MyUniqueList<Book> findAll() {
        return books;
    }
}
