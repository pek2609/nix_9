package ua.com.alevel.facade;

import ua.com.alevel.dto.BaseRequestDto;
import ua.com.alevel.dto.BaseResponseDto;

import java.io.IOException;
import java.util.Collection;

public interface BaseFacade<REQ extends BaseRequestDto, RESP extends BaseResponseDto> {

    void create(REQ req);

    void update(REQ req, String id);

    void delete(String id);

    RESP findById(String id);

    Collection<RESP> findAll() throws IOException;
}
