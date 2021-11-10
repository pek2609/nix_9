package ua.com.alevel.service.impl;

import ua.com.alevel.MyUniqueList;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

public class GenerateBookUtil {

    public static final String NAME = "name";

    public static Book generateBook(String name , MyUniqueList<Author> authors) {
        Book book = new Book();
        book.setName(name);
        book.setAuthors(authors);
        return book;
    }

}
