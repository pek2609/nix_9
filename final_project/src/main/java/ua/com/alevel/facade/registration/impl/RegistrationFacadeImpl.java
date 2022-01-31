package ua.com.alevel.facade.registration.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.registration.RegistrationFacade;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.service.client.ClientService;
import ua.com.alevel.web.dto.client.ClientRequestDto;

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final ClientService clientService;

    public RegistrationFacadeImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void register(ClientRequestDto clientRegisterDto) {
        Client client = new Client();
        client.setFirstName(clientRegisterDto.getFirstName());
        client.setLastName(clientRegisterDto.getLastName());
        client.setPhoneNumber(clientRegisterDto.getPhoneNumber());
        client.setSex(clientRegisterDto.getSex());
        client.setEmail(clientRegisterDto.getEmail());
        client.setPassword(clientRegisterDto.getPassword());
        clientService.create(client);
    }
}
