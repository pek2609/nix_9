package ua.com.alevel.facade.client;

import ua.com.alevel.facade.BaseFacade;
import ua.com.alevel.web.dto.client.ChangePasswordRequestDto;
import ua.com.alevel.web.dto.client.ClientProfileRequestDto;
import ua.com.alevel.web.dto.client.ClientRegisterRequestDto;
import ua.com.alevel.web.dto.client.ClientResponseDto;

public interface ClientFacade extends BaseFacade<ClientRegisterRequestDto, ClientResponseDto> {

    void ban(Long clientId);

    void unban(Long clientId);

    void updateProfile(ClientProfileRequestDto dto, Long id);

    void changePassword(ChangePasswordRequestDto dto);
}
