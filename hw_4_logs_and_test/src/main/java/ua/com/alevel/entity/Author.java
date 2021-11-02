package ua.com.alevel.entity;

import ua.com.alevel.MyList;

public class Author extends BaseEntity {

    private String firstName;
    private String lastName;
    private MyList<Book> books;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public MyList<Book> getBooks() {
        return books;
    }

    public void setBooks(MyList<Book> books) {
        this.books = books;
    }
}
