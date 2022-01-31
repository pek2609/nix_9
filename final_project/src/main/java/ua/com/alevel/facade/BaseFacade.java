package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.web.dto.DtoRequest;
import ua.com.alevel.web.dto.DtoResponse;
import ua.com.alevel.web.dto.datatable.PageData;

public interface BaseFacade<REQ extends DtoRequest, RES extends DtoResponse> {

    void create(REQ req);

    void update(REQ req, Long id);

    void delete(Long id);

    RES findById(Long id);

    PageData<RES> findAll(WebRequest request);

}
