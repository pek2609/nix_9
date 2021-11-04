package ua.com.alevel.util;

import ua.com.alevel.MyList;
import ua.com.alevel.entity.BaseEntity;

import java.util.UUID;

public final class GenerateIdUtil {

    private GenerateIdUtil() {
    }

    public static String generateId(MyList<? extends BaseEntity> entities) {
        String id = UUID.randomUUID().toString();
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId().equals(id)) {
                return generateId(entities);
            }
        }
        return id;
    }

}
