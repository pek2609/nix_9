package ua.com.alevel.facade.client;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.BaseFacade;
import ua.com.alevel.web.dto.client.ClientRequestDto;
import ua.com.alevel.web.dto.client.ClientResponseDto;

@Service
public interface ClientFacade extends BaseFacade<ClientRequestDto, ClientResponseDto> {

    void ban(Long clientId);
    void unban(Long clientId);
}
