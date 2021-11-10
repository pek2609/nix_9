package ua.com.alevel.service.impl;

import org.junit.jupiter.api.*;
import ua.com.alevel.MyUniqueList;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;

import java.util.Arrays;
import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceImplTest {

    private final static BookService bookService = new BookServiceImpl();
    private final static AuthorService authorService = new AuthorServiceImpl();
    private final static MyUniqueList<Author> authors = new MyUniqueList<>();
    private final static int BOOKS_SIZE = 10;
    private final static int AUTHORS_SIZE = 4;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < AUTHORS_SIZE; i++) {
            Author author = GenerateAuthorUtil.generateAuthor(GenerateAuthorUtil.FIRST_NAME + i, GenerateAuthorUtil.LAST_NAME + i);
            authors.add(author);
        }
        Assertions.assertEquals(AUTHORS_SIZE, authors.size());
        for (int i = 0; i < BOOKS_SIZE; i++) {
            Book book = GenerateBookUtil.generateBook(GenerateBookUtil.NAME + i, authors);
            bookService.create(book);
        }
        Assertions.assertEquals(BOOKS_SIZE, bookService.findAll().size());
    }

    @Order(1)
    @Test
    void shouldBeCreatedWhenBookNull() {
        Book book = new Book();
        book.setName(null);
        book.setAuthors(authors);
        bookService.create(book);
        verifyBookListWhenBooksListIsNotEmpty(BOOKS_SIZE + 1);
    }

    @Order(2)
    @Test
    void shouldNotBeCreatedWhenBookBlank() {
        Book book = new Book();
        book.setName(" ");
        book.setAuthors(authors);
        String expectedMessage = "Book has blank name!";
        try {
            bookService.create(book);
        } catch (Exception e) {
            Assertions.assertEquals(expectedMessage, e.getMessage());
        }
        verifyBookListWhenBooksListIsNotEmpty(BOOKS_SIZE + 1);
    }

    @Order(3)
    @Test
    void shouldNotBeCreatedIfBookExists() {
        Book book = new Book();
        book.setName("name1");
        book.setAuthors(authors);
        String expectedMessage = "Book " + book.getName() + " with Authors " + Arrays.toString(book.getAuthors().toArray()) + " is already exist!";
        try {
            bookService.create(book);
        } catch (Exception e) {
            Assertions.assertEquals(expectedMessage, e.getMessage());
        }
        verifyBookListWhenBooksListIsNotEmpty(BOOKS_SIZE + 1);
    }

    @Order(4)
    @Test
    public void shouldBeDeleteBook() {
        String id = getRandomIdFromBookList(BOOKS_SIZE + 1);
        bookService.delete(id);
        verifyBookListWhenBooksListIsNotEmpty(BOOKS_SIZE);
    }

    @Order(5)
    @Test
    public void shouldBeFindBookWhenIdIsRandom() {
        Book book = bookService.findById(getRandomIdFromBookList(BOOKS_SIZE));
        Assertions.assertNotNull(book);
    }

    @Order(6)
    @Test
    public void shouldBeUpdateBook() {
        authors.add(new Author("1", "2"));
        String id = getRandomIdFromBookList(BOOKS_SIZE);
        Book book = bookService.findById(id);
        Book other = new Book();
        other.setName("otherName");
        other.setAuthors(authors);
        other.setId(book.getId());
        bookService.update(other);
        book = bookService.findById(id);
        Assertions.assertEquals("otherName", book.getName());
        Assertions.assertEquals(authors, book.getAuthors());
    }

    @Order(7)
    @Test
    void shouldNotBeUpdatedWhenBookBlank() {
        String id = getRandomIdFromBookList(BOOKS_SIZE);
        Book book = bookService.findById(id);
        Book other = new Book();
        other.setName(" ");
        other.setAuthors(authors);
        other.setId(book.getId());
        String expectedMessage = "Book has blank name!";
        try {
            bookService.update(other);
        } catch (Exception e) {
            Assertions.assertEquals(expectedMessage, e.getMessage());
        }
        verifyBookListWhenBooksListIsNotEmpty(BOOKS_SIZE);
    }

    @Order(8)
    @Test
    void shouldNotBeUpdatedIfBookExists() {
        String id = getRandomIdFromBookList(BOOKS_SIZE);
        Book book = bookService.findById(id);
        Book other = new Book();
        other.setName("name1");
        other.setAuthors(authors);
        other.setId(book.getId());
        String expectedMessage = "Book " + other.getName() + " with Authors " + Arrays.toString(book.getAuthors().toArray()) + " is already exist!";
        try {
            bookService.update(other);
        } catch (Exception e) {
            Assertions.assertEquals(expectedMessage, e.getMessage());
        }
        verifyBookListWhenBooksListIsNotEmpty(BOOKS_SIZE);
    }

    private String getRandomIdFromBookList(int size) {
        Random random = new Random();
        int index = random.nextInt(size - 1);
        return bookService.findAll().get(index).getId();
    }

    private void verifyBookListWhenBooksListIsNotEmpty(int size) {
        MyUniqueList<Book> books = bookService.findAll();
        Assertions.assertTrue(books.size() != 0);
        Assertions.assertEquals(size, bookService.findAll().size());
    }
}