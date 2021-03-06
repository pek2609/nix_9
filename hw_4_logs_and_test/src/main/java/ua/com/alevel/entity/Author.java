package ua.com.alevel.entity;

import java.util.ArrayList;
import java.util.Objects;

public class Author extends BaseEntity {

    private String firstName;
    private String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author() {

    }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + getId() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '}';
    }

    public boolean isBlank() {
        if(this.firstName!=null&&this.lastName!=null){
            return this.firstName.isBlank() || this.lastName.isBlank();
        }
        return false;
    }
}
