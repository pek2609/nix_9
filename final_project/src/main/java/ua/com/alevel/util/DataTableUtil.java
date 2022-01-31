package ua.com.alevel.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;

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
        dataTableResponse.setTotalPages(pageEntity.getTotalPages());
        dataTableResponse.setItems(pageEntity.getContent());
        return dataTableResponse;
    }
}
