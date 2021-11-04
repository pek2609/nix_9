package ua.com.alevel.controller;

import ua.com.alevel.MyList;
import ua.com.alevel.TaskRunner;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;
import ua.com.alevel.service.impl.AuthorServiceImpl;
import ua.com.alevel.service.impl.BookServiceImpl;
import ua.com.alevel.util.ServiceHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BookController implements TaskRunner {

    private static final String menuString = """
            -------------Menu-------------
            1 - create book
            2 - update book
            3 - delete book
            4 - findById
            5 - findAll
            0 - Back
            ------------------------------
            """;

    private final BookService bookService = new BookServiceImpl();
    private final AuthorService authorService = new AuthorServiceImpl();

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int op;
        while (true) {
            try {
                System.out.println(menuString);
                System.out.println("Enter your option");
                op = Integer.parseInt(reader.readLine());
                if (op == 0) {
                    break;
                }
                callByOption(reader, op);
            } catch (Exception e) {
                System.out.println("problem: = " + e.getMessage());
            }
        }
    }

    private void callByOption(BufferedReader reader, int op) {
        switch (op) {
            case 1 -> create(reader);
            case 2 -> update(reader);
            case 3 -> delete(reader);
            case 4 -> findById(reader);
            case 5 -> findAll();
            default -> System.out.println("Unexpected option..");
        }
    }

    private void findAll() {
        MyList<Book> books = bookService.findAll();
        if (books != null && books.size() != 0) {
            for (int i = 0; i < books.size(); i++) {
                System.out.println(books.get(i).toString());
            }
        } else {
            System.out.println("There is no books in the book list");
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            Book book = bookService.findById(id);
            System.out.println("book = " + book);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            bookService.delete(id);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.println("Please, enter book id , that you want to update");
            String idBook = reader.readLine();
            bookService.findById(idBook);
            System.out.println("Please, enter book name");
            String name = reader.readLine();
            Book book = new Book();
            book.setId(idBook);
            book.setName(name);
            Author author = inputAuthor(reader);
            book.setAuthor(author);
            bookService.update(book);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void create(BufferedReader reader) {
        try {
            System.out.println("Please, enter book name");
            String name = reader.readLine();
            Book book = new Book();
            book.setName(name);
            Author author = inputAuthor(reader);
            book.setAuthor(author);
            bookService.create(book);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private Author inputAuthor(BufferedReader reader) throws IOException {
        while (true) {
            if (authorService.findAll().size() == 0) {
                return inputAuthorFromConsole(reader);
            }
            System.out.println("Do you want to choose author from existing authors?");
            System.out.println("Please, enter (Y/N)");
            String res = reader.readLine();
            switch (res) {
                case "Y" -> {
                    return chooseAuthorFromExistingAuthors(reader);
                }
                case "N" -> {
                    return inputAuthorFromConsole(reader);
                }
                default -> System.out.println("Unexpected option...");
            }
        }
    }

    private Author inputAuthorFromConsole(BufferedReader reader) throws IOException {
        System.out.println("Please, enter author of the book");
        System.out.println("Please, enter firstName");
        String firstName = reader.readLine();
        System.out.println("Please, enter lastName");
        String lastName = reader.readLine();
        Author author = new Author(firstName, lastName);
        if (ServiceHelper.isExist(author, authorService)) {
            System.out.println("Your author exists. Choose him");
            author = chooseAuthorFromExistingAuthors(reader);
        } else {
            authorService.create(author);
        }
        return author;
    }

    private Author chooseAuthorFromExistingAuthors(BufferedReader reader) throws IOException {
        MyList<Author> authors = authorService.findAll();
        for (int i = 0; i < authors.size(); i++) {
            System.out.println(authors.get(i).toString());
        }
        System.out.println("Please, enter author id ");
        String idAuthor = reader.readLine();
        return authorService.findById(idAuthor);
    }

}
