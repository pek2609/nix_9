package ua.com.alevel.controller;

import ua.com.alevel.MyList;
import ua.com.alevel.TaskRunner;
import ua.com.alevel.entity.Author;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.impl.AuthorServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AuthorController implements TaskRunner {

    private static final String menuString = """
            -------------Menu-------------
            1 - create author
            2 - update author
            3 - delete author
            4 - findById
            5 - findAll
            0 - Back
            ------------------------------
            """;

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
                if(op==0){
                    break;
                }
                callByOption(reader, op);
            } catch (Exception e) {
                System.out.println("problem: = " + e.getMessage());
            }
        }
    }

    private void callByOption(BufferedReader reader, int op){
        switch (op){
            case 1 -> create(reader);
            case 2 -> update(reader);
            case 3 -> delete(reader);
            case 4 -> findById(reader);
            case 5 -> findAll();
            default -> System.out.println("Unexpected option..");
        }
    }

    private void findAll() {
        MyList<Author> books = authorService.findAll();
        if (books != null && books.size() != 0) {
            for (int i = 0; i < books.size(); i++) {
                System.out.println(books.get(i).toString());
            }
        } else {
            System.out.println("There is no authors in the book list");
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            Author author = authorService.findById(id);
            System.out.println("author = " + author);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            authorService.delete(id);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.println("Please, enter author id ");
            String idAuthor = reader.readLine();
            System.out.println("Please, enter firstName");
            String firstName = reader.readLine();
            System.out.println("Please, enter lastName");
            String lastName = reader.readLine();
            Author author = new Author(firstName, lastName);
            author.setId(idAuthor);
            authorService.update(author);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void create(BufferedReader reader) {
        try {
            System.out.println("Please, enter firstName");
            String firstName = reader.readLine();
            System.out.println("Please, enter lastName");
            String lastName = reader.readLine();
            authorService.create(new Author(firstName, lastName));
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }
}
