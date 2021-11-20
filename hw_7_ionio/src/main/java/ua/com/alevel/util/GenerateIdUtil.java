package ua.com.alevel.util;

import ua.com.alevel.MyUniqueList;
import ua.com.alevel.entity.BaseEntity;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public final class GenerateIdUtil {

    private GenerateIdUtil() {
    }

    public static <E extends BaseEntity> String generateId(Collection<E> entities) {
        String id = UUID.randomUUID().toString();
        for (E e : entities) {
            if (e.getId().equals(id)) {
                return generateId(entities);
            }
        }
        return id;
    }

}
