package ua.com.alevel.controller;

import ua.com.alevel.MyUniqueList;
import ua.com.alevel.TaskRunner;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.BookService;
import ua.com.alevel.service.impl.BookServiceImpl;
import ua.com.alevel.util.AuthorReader;

import java.io.BufferedReader;
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
        MyUniqueList<Book> books = bookService.findAll();
        if (books != null && books.size() != 0) {
            for (Book book : books) {
                System.out.println(book);
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
            if (book.isBlank()) {
                throw new RuntimeException("Book has blank name!");
            }
            MyUniqueList<Author> authors = AuthorReader.inputAuthors(reader);
            book.setAuthors(authors);
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
            if (book.isBlank()) {
                throw new RuntimeException("Book has blank name!");
            }
            System.out.println("Please, enter book's authors");
            MyUniqueList<Author> authors = AuthorReader.inputAuthors(reader);
            book.setAuthors(authors);
            bookService.create(book);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }
}
