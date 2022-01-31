package ua.com.alevel.facade.registration;

import ua.com.alevel.web.dto.client.ClientRequestDto;

public interface RegistrationFacade {

    void register(ClientRequestDto clientRegisterDto);
}
