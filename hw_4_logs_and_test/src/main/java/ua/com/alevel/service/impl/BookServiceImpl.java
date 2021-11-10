package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.com.alevel.MyUniqueList;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.dao.impl.AuthorDaoImpl;
import ua.com.alevel.dao.impl.BookDaoImpl;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.BookService;
import ua.com.alevel.util.CheckExistEntityUtil;

import java.util.Arrays;

public class BookServiceImpl implements BookService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final BookDao bookDao = new BookDaoImpl();
    private final AuthorDao authorDao = new AuthorDaoImpl();

    @Override
    public void create(Book book) {
        LOGGER_INFO.info("Book creation started");
        checkForBlankBook(book);
        checkIfBookExists(book);
        bookDao.create(book);
        MyUniqueList<Author> authors = book.getAuthors();
        createNotExistAuthors(authors);
        LOGGER_INFO.info("Book creation ended, id = " + book.getId());
    }

    @Override
    public void update(Book book) {
        LOGGER_INFO.info("Book updating started, id = " + book.getId());
        checkForBlankBook(book);
        checkIfBookExists(book);
        bookDao.update(book);
        MyUniqueList<Author> authors = book.getAuthors();
        createNotExistAuthors(authors);
        LOGGER_INFO.info("Book updating ended, id = " + book.getId());
    }

    @Override
    public void delete(String id) {
        LOGGER_WARN.warn("Book deleting started , id = " + id);
        bookDao.delete(id);
        LOGGER_WARN.warn("Book deleting ended , id = " + id);
    }

    @Override
    public Book findById(String id) {
        try {
            return bookDao.findById(id);
        } catch (Exception e) {
            LOGGER_ERROR.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public MyUniqueList<Book> findAll() {
        return bookDao.findAll();
    }

    private void createNotExistAuthors(MyUniqueList<Author> authors) {
        MyUniqueList<Author> all = authorDao.findAll();
        for (Author author : authors) {
            if (!CheckExistEntityUtil.isExist(author, all)) {
                LOGGER_INFO.info("Author creation started");
                authorDao.create(author);
                LOGGER_INFO.info("Author creation ended, id = " + author.getId());
            }
        }
    }

    private void checkForBlankBook(Book book) {
        if(book.isBlank()){
            LOGGER_ERROR.error("Book has blank name");
            throw new RuntimeException("Book has blank name!");
        }
    }

    private void checkIfBookExists(Book book) {
        if (CheckExistEntityUtil.isExist(book, bookDao.findAll())) {
            LOGGER_ERROR.error("Book " + book.getName() + " with Authors " + Arrays.toString(book.getAuthors().toArray()) + " is already exist");
            throw new RuntimeException("Book " + book.getName() + " with Authors " + Arrays.toString(book.getAuthors().toArray()) + " is already exist!");
        }
    }
}
