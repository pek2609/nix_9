package ua.com.alevel.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.MyUniqueList;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.impl.AuthorDaoImpl;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;
import ua.com.alevel.util.CheckExistEntityUtil;

public class AuthorServiceImpl implements AuthorService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final AuthorDao authorDao = new AuthorDaoImpl();
    private final BookService bookService = new BookServiceImpl();

    @Override
    public void create(Author author) {
        LOGGER_INFO.info("Author creation started");
        if (CheckExistEntityUtil.isExist(author, authorDao.findAll())) {
            LOGGER_ERROR.error("Author " + author.getFirstName() + " " + author.getLastName() + " is already exist!");
            throw new RuntimeException("Author " + author.getFirstName() + " " + author.getLastName() + " is already exist!");
        }
        if (author.isBlank()) {
            LOGGER_ERROR.error("Author has blank name or surname");
            throw new RuntimeException("Author has blank name or surname!");
        }
        authorDao.create(author);
        LOGGER_INFO.info("Author creation ended, id = " + author.getId());
    }

    @Override
    public void update(Author author) {
        LOGGER_INFO.info("Author updating started, id = " + author.getId());
        if (author.isBlank()) {
            LOGGER_ERROR.error("Author has blank name or surname");
            throw new RuntimeException("Author " + author.getFirstName() + " " + author.getLastName() + " has blank name or surname!");
        }
        if (CheckExistEntityUtil.isExist(author, authorDao.findAll())) {
            LOGGER_ERROR.error("Author " + author.getFirstName() + " " + author.getLastName() + " is already exist!");
            throw new RuntimeException("Author " + author.getFirstName() + " " + author.getLastName() + " is already exist!");
        }
        authorDao.update(author);
        LOGGER_INFO.info("Author updating ended, id = " + author.getId());
    }

    @Override
    public void delete(String id) {
        LOGGER_WARN.warn("Deleting author started, id = " +id);
        deleteBooksForAuthor(id);
        authorDao.delete(id);
        LOGGER_WARN.warn("Deleting author ended, id = " +id);
    }

    @Override
    public Author findById(String id) {
        try {
            return authorDao.findById(id);
        } catch (Exception e) {
            LOGGER_ERROR.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public MyUniqueList<Author> findAll() {
        return authorDao.findAll();
    }

    private void deleteBooksForAuthor(String idAuthor) {
        MyUniqueList<Book> books = bookService.findAll();
        Author author = findById(idAuthor);
        for (Book book : books) {
            MyUniqueList<Author> authors = book.getAuthors();
            authors.remove(author);
        }
        for (Book book : books) {
            MyUniqueList<Author> authors = book.getAuthors();
            if (authors.size()==0) {
                books.remove(book);
            }
        }
    }
}

