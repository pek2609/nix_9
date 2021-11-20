package ua.com.alevel.util;

import ua.com.alevel.entity.BaseEntity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public final class Parser {

    private Parser() {
    }

    public static <T extends BaseEntity> Collection<T> convertToEntities(Class<T> tClass, List<String[]> list) {
        Collection<T> collection = new LinkedHashSet<>();
        List<Method> setters = Arrays.stream(tClass.getDeclaredMethods()).filter(x -> x.getName().startsWith("set")).collect(Collectors.toList());
        for (int i = 1; i < list.size(); i++) {
            String[] strings = list.get(i);
            if (strings.length - 1 != setters.size()) {
                throw new RuntimeException("Can't parse provided " + strings.length + "values in " + setters.size() + " class fields");
            }
            try {
                T t = tClass.newInstance();
                t.setId(strings[0]);
                for (int j = 1, k = 0; j < strings.length; j++) {
                    setters.get(k++).invoke(t, strings[j]);
                }
                collection.add(t);
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
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
            res.add(convertToString(tClass, t));
        }
        return res;
    }

    public static <T extends BaseEntity> String[] convertToString(Class<T> tClass, T t) {
        List<Method> getters = Arrays.stream(tClass.getDeclaredMethods()).filter(x -> x.getName().startsWith("get")).collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(t.getId()).append(" ");
        for (Method getter : getters) {
            try {
                stringBuilder.append(getter.invoke(t)).append(" ");
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString().split(" ");
    }


}
