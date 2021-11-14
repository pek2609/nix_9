package ua.com.alevel;

import ua.com.alevel.examples.constructors.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CollectionMain {

    public static void main(String[] args) throws ParseException {
        List<TaskRunner> list = new ArrayList<>();
        list.add(new DefaultConstructor());
        list.add(new CapacityConstructor());
        list.add(new NumberArrayConstructor());
        list.add(new NumberArraysConstructor());
        list.add(new MathSetConstructor());
        list.add(new MathSetsConstructor());
        ConsoleHelper.run(list);
    }
}
