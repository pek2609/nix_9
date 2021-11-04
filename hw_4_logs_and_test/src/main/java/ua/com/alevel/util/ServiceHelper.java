package ua.com.alevel.util;

import ua.com.alevel.MyList;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.service.BaseService;

public final class ServiceHelper {

    private ServiceHelper() {

    }

    public static <E extends BaseEntity, S extends BaseService<E>> boolean isExist(E e, S s) {
        MyList<E> all = s.findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).equals(e)) {
                return true;
            }
        }
        return false;
    }

}
