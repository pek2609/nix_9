package ua.com.alevel.entity;

import java.util.Objects;

public class Book extends BaseEntity {

    private String name;
    private Author author;

    public Book() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", author=" + author +
                '}';
    }

    public boolean isBlank() {
        return this.name.isBlank();
    }
}
