package ua.com.alevel;


import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws IOException {
//        List<String[]> data = new ArrayList<>();
//        List<Book> books = generateBooks();
//        String[] head = {"id", "name"};
//        data.add(head);
//        for (Book book : books) {
//            String[] array = new String[2];
//            array[0] = book.getId();
//            array[1] = book.getName();
//            data.add(array);
//        }
//        try (FileWriter fileWriter = new FileWriter("demo.csv")) {
//            CSVWriter csvWriter = new CSVWriter(fileWriter);
//            csvWriter.writeAll(data);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        try (FileReader fileReader = new FileReader("demo.csv")) {
            CSVReader csvReader = new CSVReader(fileReader);
            List<String[]> list = csvReader.readAll();
            for (String[] strings : list) {
                System.out.println(Arrays.toString(strings));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static List<Book> generateBooks() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setName("name" + i);
            book.setId(UUID.randomUUID().toString());
            MyUniqueList<Author> authors = new MyUniqueList<>();
            authors.add(new Author("firstName" + i, "lastName" + i));
            book.setAuthors(authors);
            books.add(book);
        }
        return books;
    }
}
