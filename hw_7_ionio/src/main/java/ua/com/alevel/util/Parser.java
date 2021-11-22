package ua.com.alevel.util;

import ua.com.alevel.entity.BaseEntity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public final class Parser {

    private Parser() {
    }

    public static <T extends BaseEntity> Collection<T> convertToEntities(Class<T> tClass, List<String[]> list) {
        Collection<T> collection = new LinkedHashSet<>();
        List<Field> allFields = getAllFields(tClass);
        for (int i = 1; i < list.size(); i++) {
            String[] strings = list.get(i);
            if (strings.length != allFields.size()) {
                throw new RuntimeException("Can't parse provided " + strings.length + "values in " + allFields.size() + " class fields");
            }
            try {
                T t = tClass.getDeclaredConstructor().newInstance();
                for (int j = 0; j < allFields.size(); j++) {
                    allFields.get(j).setAccessible(true);
                    allFields.get(j).set(t, strings[j]);
                }
                collection.add(t);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return collection;
    }


    public static <T extends BaseEntity> List<String[]> convertToStrings(Class<T> tClass, Collection<T> collection) {
        List<String[]> res = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id").append(" ");
        for (Field declaredField : tClass.getDeclaredFields()) {
            stringBuilder.append(declaredField.getName()).append(" ");
        }
        res.add(stringBuilder.toString().split(" "));
        for (T t : collection) {
            res.add(convertToString(t));
        }
        return res;
    }

    public static <T extends BaseEntity> String[] convertToString(T t) {
        List<Field> allFields = getAllFields(t.getClass());
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Field field : allFields) {
                field.setAccessible(true);
                stringBuilder.append(field.get(t)).append(" ");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString().split(" ");
    }

    private static <T> List<Field> getAllFields(Class<T> tClass) {
        if (tClass == null) {
            return Collections.emptyList();
        }
        List<Field> result = new ArrayList<>(getAllFields(tClass.getSuperclass()));
        List<Field> filteredFields = Arrays.stream(tClass.getDeclaredFields()).collect(Collectors.toList());
        result.addAll(filteredFields);
        return result;
    }

}
