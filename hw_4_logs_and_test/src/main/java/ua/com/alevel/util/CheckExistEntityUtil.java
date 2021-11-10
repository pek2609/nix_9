package ua.com.alevel.util;

import ua.com.alevel.MyUniqueList;
import ua.com.alevel.entity.BaseEntity;

public final class CheckExistEntityUtil {

    private CheckExistEntityUtil() {

    }

    public static <E extends BaseEntity> boolean isExist(E e, MyUniqueList<E> set) {
        for (E el : set) {
            if (el.equals(e)) {
                return true;
            }
        }
        return false;
    }

}
