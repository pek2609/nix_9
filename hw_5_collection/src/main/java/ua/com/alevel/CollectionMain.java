package ua.com.alevel;

import ua.com.alevel.examples.constructors.*;
import ua.com.alevel.examples.methods.*;
import ua.com.alevel.util.NumberReaderUtil;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
        list.add(new AddNumberMethod());
        list.add(new AddNumbersMethod());
        list.add(new JoinMathSetMethod());
        list.add(new JoinMathSetsMethod());
        list.add(new IntersectMathSetMethod());
        list.add(new IntersectMathSetsMethod());
        list.add(new SortWholeDescMethod());
        list.add(new SortIntervalDescMethod());
        list.add(new SortFromValueDescMethod());
        list.add(new SortWholeAscMethod());
        list.add(new SortIntervalAscMethod());
        list.add(new SortFromValueAscMethod());
        list.add(new GetByIndexMethod());
        list.add(new GetMaxMethod());
        list.add(new GetMinMethod());
        list.add(new GetAverageMethod());
        list.add(new GetMedianMethod());
        list.add(new ToArrayMethod());
        list.add(new ToArrayIntervalMethod());
        list.add(new CutMethod());
        list.add(new ClearMethod());
        list.add(new ClearNumbersMethod());
        ConsoleHelper.run(list);
    }
}
