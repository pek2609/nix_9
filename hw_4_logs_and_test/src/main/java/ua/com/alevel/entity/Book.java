package ua.com.alevel.entity;

import ua.com.alevel.MyUniqueList;

import java.util.Arrays;
import java.util.Objects;

public class Book extends BaseEntity {

    private String name;
    private MyUniqueList<Author> authors;

    public Book() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyUniqueList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(MyUniqueList<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name) && Objects.equals(authors, book.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, authors);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", authors=" + Arrays.toString(authors.toArray()) +
                '}';
    }

    public boolean isBlank() {
        if (name!=null) {
            return name.isBlank();
        }
        return false;
    }
}
