package ua.com.alevel.service.impl;

import ua.com.alevel.entity.Author;

public class GenerateAuthorUtil {

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";

    public static Author generateAuthor() {
        return new Author(FIRST_NAME, LAST_NAME);
    }

    public static Author generateAuthor(String firstName, String lastName) {
        return new Author(firstName, lastName);
    }

}
