package ua.com.alevel.service.impl;

import org.junit.jupiter.api.*;
import ua.com.alevel.MyUniqueList;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;

import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorServiceImplTest {

    private final static AuthorService authorService = new AuthorServiceImpl();
    private final static int AUTHORS_SIZE = 10;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < AUTHORS_SIZE; i++) {
            Author author = GenerateAuthorUtil.generateAuthor(GenerateAuthorUtil.FIRST_NAME + i, GenerateAuthorUtil.LAST_NAME + i);
            authorService.create(author);
        }
        Assertions.assertEquals(AUTHORS_SIZE, authorService.findAll().size());
    }
    @Order(1)
    @Test
    void shouldBeCreatedWhenAuthorNull() {
        Author author = new Author();
        author.setFirstName(null);
        author.setLastName(null);
        authorService.create(author);
        verifyAuthorListWhenAuthorsListIsNotEmpty(AUTHORS_SIZE +1);
    }

    @Order(2)
    @Test
    void shouldNotBeCreatedWhenAuthorBlank() {
        Author author = new Author();
        author.setFirstName("");
        author.setLastName(" ");
        String expectedMessage = "Author has blank name or surname!";
        try {
            authorService.create(author);
        } catch (Exception e) {
            Assertions.assertEquals(expectedMessage, e.getMessage());
        }
        verifyAuthorListWhenAuthorsListIsNotEmpty(AUTHORS_SIZE+1);
    }

    @Order(3)
    @Test
    void shouldNotBeCreatedIfAuthorExists() {
        Author author = new Author();
        author.setFirstName("firstName1");
        author.setLastName("lastName1");
        String expectedMessage = "Author " + author.getFirstName() + " " + author.getLastName() + " is already exist!";

        try {
            authorService.create(author);
        } catch (Exception e) {
            Assertions.assertEquals(expectedMessage, e.getMessage());
        }
        verifyAuthorListWhenAuthorsListIsNotEmpty(AUTHORS_SIZE+1);
    }

    @Order(4)
    @Test
    public void shouldBeDeleteAuthor() {
        String id = getRandomIdFromAuthorList(AUTHORS_SIZE);
        authorService.delete(id);
        verifyAuthorListWhenAuthorsListIsNotEmpty(AUTHORS_SIZE);
    }

    @Order(5)
    @Test
    public void shouldBeDeleteAuthorsFromBooksWhenDeleteAuthor() {
        String id = getRandomIdFromAuthorList(AUTHORS_SIZE);
        Book book = new Book();
        book.setAuthors(authorService.findAll());
        authorService.delete(id);
        MyUniqueList<Author> authors = book.getAuthors();
        Assertions.assertEquals(AUTHORS_SIZE-1, authors.size());
    }

    @Order(6)
    @Test
    public void shouldBeFindAuthorWhenIdIsRandom() {
        Author author = authorService.findById(getRandomIdFromAuthorList(AUTHORS_SIZE-1));

        Assertions.assertNotNull(author);
    }

    @Order(7)
    @Test
    public void shouldBeUpdateAuthor() {
        String id = getRandomIdFromAuthorList(AUTHORS_SIZE-1);
        Author author = authorService.findById(id);
        updateAuthor(author, "1", "2");
        author = authorService.findById(id);
        Assertions.assertEquals("1", author.getFirstName());
        Assertions.assertEquals("2", author.getLastName());
    }

    @Order(8)
    @Test
    public void shouldNotBeUpdateWhenAuthorBlank() {
        String id = getRandomIdFromAuthorList(AUTHORS_SIZE-1);
        String firstName = "";
        String lastName = "";
        Author author = authorService.findById(id);
        String expectedMessage = "Author " + firstName + " " + lastName + " has blank name or surname!";
        try {
            updateAuthor(author, firstName, lastName);
        } catch (Exception e) {
            Assertions.assertEquals(expectedMessage, e.getMessage());
        }
        Author other = authorService.findById(id);
        Assertions.assertEquals(author, other);
    }

    @Order(9)
    @Test
    public void shouldNotBeUpdatedIfAuthorExists() {
        String id = getRandomIdFromAuthorList(AUTHORS_SIZE-1);
        Author author = authorService.findById(id);
        String expectedMessage = "Author " + author.getFirstName() + " " + author.getLastName() + " is already exist!";
        try {
            updateAuthor(author, author.getFirstName(), author.getLastName());
        } catch (Exception e) {
            Assertions.assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Order(10)
    @Test
    public void shouldBeUpdateAuthorsFromBooksWhenUpdateAuthor() {
        String id = getRandomIdFromAuthorList(AUTHORS_SIZE - 1);
        Author author = authorService.findById(id);
        Book book = new Book();
        book.setAuthors(authorService.findAll());
        updateAuthor(author, "hello" , "hello");
        MyUniqueList<Author> authors = book.getAuthors();
        Assertions.assertEquals(authors, authorService.findAll());
    }

    private String getRandomIdFromAuthorList(int listSize) {
        Random random = new Random();
        int index = random.nextInt(listSize-1);
        return authorService.findAll().get(index).getId();
    }

    private void updateAuthor(Author author, String firstName, String lastname){
        Author other = new Author(firstName, lastname);
        other.setId(author.getId());
        authorService.update(other);
    }

    private void verifyAuthorListWhenAuthorsListIsNotEmpty(int size) {
        MyUniqueList<Author> users = authorService.findAll();
        Assertions.assertTrue(users.size()!=0);
        Assertions.assertEquals(size, authorService.findAll().size());
    }
}