package ua.com.alevel.util;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.DtoResponse;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;

public final class FacadeUtil {

    private FacadeUtil() {
    }

    public static DataTableRequest getDTReqFromPageAndSortData(PageAndSizeData pageAndSizeData, SortData sortData) {
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setOrder(sortData.getOrder());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSize(pageAndSizeData.getSize());
        return dataTableRequest;
    }

    public static <RES extends DtoResponse> PageData<RES> getPageDataFromDTResp(List<RES> items, PageAndSizeData pageAndSizeData, SortData sortData) {
        PageData<RES> pageData = new PageData<>();
        pageData.setItems(items);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        return pageData;
    }
}
