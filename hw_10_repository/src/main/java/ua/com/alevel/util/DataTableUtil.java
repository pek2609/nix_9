package ua.com.alevel.util;

import org.apache.commons.collections4.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.Group;

import java.util.HashMap;
import java.util.Map;

public final class DataTableUtil {

    private DataTableUtil() {
    }

    public static PageRequest formPageableByRequest(DataTableRequest dataTableRequest) {
        int page = dataTableRequest.getPage() - 1;
        int size = dataTableRequest.getSize();
        String sortParam = dataTableRequest.getSort();
        String orderParam = dataTableRequest.getOrder();


        Sort sort = orderParam.equals("desc")
                ? Sort.by(sortParam).descending()
                : Sort.by(sortParam).ascending();

        if (MapUtils.isNotEmpty(dataTableRequest.getRequestParamMap())) {
            System.out.println("dataTableRequest = " + dataTableRequest.getRequestParamMap());
        }

        return PageRequest.of(page, size, sort);
    }

    public static <T extends BaseEntity> DataTableResponse<T> formResponse(Page<T> pageEntity, DataTableRequest dataTableRequest) {
        int page = (dataTableRequest.getPage() - 1) * dataTableRequest.getSize();
        int size = dataTableRequest.getSize();
        String sortParam = dataTableRequest.getSort();
        String orderParam = dataTableRequest.getOrder();
        DataTableResponse<T> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setSort(sortParam);
        dataTableResponse.setOrder(orderParam);
        dataTableResponse.setPageSize(size);
        dataTableResponse.setCurrentPage(page);
        dataTableResponse.setItemsSize(pageEntity.getTotalElements());
        dataTableResponse.setTotalPageSize(pageEntity.getTotalPages());
        dataTableResponse.setItems(pageEntity.getContent());
        return dataTableResponse;
    }

    public static void setOtherParamMapStudCount(DataTableResponse<Group> groupDataTableResponse) {
        Map<Object, Object> otherParamMap = new HashMap<>();
        groupDataTableResponse.getItems().forEach(it-> otherParamMap.put(it.getId(), it.getStudents().size()));
        groupDataTableResponse.setOtherParamMap(otherParamMap);
    }
}
