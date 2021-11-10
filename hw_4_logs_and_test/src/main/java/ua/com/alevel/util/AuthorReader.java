package ua.com.alevel.util;

import ua.com.alevel.MyUniqueList;
import ua.com.alevel.entity.Author;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.impl.AuthorServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;

public final class AuthorReader {

    private static final AuthorService authorService = new AuthorServiceImpl();

    private AuthorReader() {
    }

    public static MyUniqueList<Author> inputAuthors(BufferedReader reader) throws IOException {
        MyUniqueList<Author> authors = new MyUniqueList<>();
        while (true) {
            System.out.println("Please, enter number of authors");
            int numOfAuth = Integer.parseInt(reader.readLine());
            try {
                if (numOfAuth <= 0) {
                    throw new RuntimeException("Number of authors must be greater then 0");
                }
                for (int i = 0; i < numOfAuth; i++) {
                    Author author;
                    System.out.println(i + 1 + "Author:");
                    if (authorService.findAll().size() == 0) {
                        author = inputAuthorFromConsole(reader);
                    } else {
                        author = inputAuthorDependsOnOption(reader);
                    }
                    authors.add(author);
                }
                return authors;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                authors.clear();
            }
        }
    }

    private static Author inputAuthorFromConsole(BufferedReader reader) throws IOException {
        System.out.println("Please, enter author of the book");
        System.out.println("Please, enter firstName");
        String firstName = reader.readLine();
        System.out.println("Please, enter lastName");
        String lastName = reader.readLine();
        Author author = new Author(firstName, lastName);
        if (author.isBlank()) {
            throw new RuntimeException("Author " + author.getFirstName() + " " + author.getLastName() + " has blank name or surname!");
        }
        if (CheckExistEntityUtil.isExist(author, authorService.findAll())) {
            System.out.println("Your author exists. Choose him");
            author = chooseAuthorFromExistingAuthors(reader);
        }
        return author;
    }

    private static Author chooseAuthorFromExistingAuthors(BufferedReader reader) throws IOException {
        MyUniqueList<Author> all = authorService.findAll();
        for (Author author : all) {
            System.out.println(author.toString());
        }
        System.out.println("Please, enter author id ");
        String idAuthor = reader.readLine();
        return authorService.findById(idAuthor);
    }

    private static Author inputAuthorDependsOnOption(BufferedReader reader) throws IOException {
        while (true) {
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
}
