package ua.com.alevel.facade.client.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.WrongPasswordException;
import ua.com.alevel.facade.client.ClientFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.service.user.ClientService;
import ua.com.alevel.util.FacadeUtil;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.web.dto.client.ChangePasswordRequestDto;
import ua.com.alevel.web.dto.client.ClientProfileRequestDto;
import ua.com.alevel.web.dto.client.ClientRegisterRequestDto;
import ua.com.alevel.web.dto.client.ClientResponseDto;
import ua.com.alevel.web.dto.datatable.PageAndSizeData;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.datatable.SortData;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientFacadeImpl implements ClientFacade {

    private final ClientService clientService;
    private final BCryptPasswordEncoder encoder;

    public ClientFacadeImpl(ClientService clientService, BCryptPasswordEncoder encoder) {
        this.clientService = clientService;
        this.encoder = encoder;
    }

    @Override
    public void create(ClientRegisterRequestDto clientRequestDto) {
        Client client = getClientFromRequestDto(clientRequestDto);
        clientService.create(client);
    }

    @Override
    public void update(ClientRegisterRequestDto clientRequestDto, Long id) {
        Client client = getClientFromRequestDto(clientRequestDto);
        client.setId(id);
        clientService.update(client);
    }

    @Override
    public void delete(Long id) {
        clientService.delete(id);
    }

    @Override
    public ClientResponseDto findById(Long id) {
        return new ClientResponseDto(clientService.findById(id));
    }

    @Override
    public PageData<ClientResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);

        DataTableResponse<Client> all = clientService.findAll(dataTableRequest);
        List<ClientResponseDto> clients = all.getItems()
                .stream()
                .map(ClientResponseDto::new)
                .collect(Collectors.toList());

        PageData<ClientResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(clients, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public void ban(Long clientId) {
        clientService.ban(clientId);
    }

    @Override
    public void unban(Long clientId) {
        clientService.unban(clientId);
    }

    @Override
    public void updateProfile(ClientProfileRequestDto dto, Long id) {
        Client existing = clientService.findById(id);
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setBirthDate(dto.getBirthDate());
        existing.setPhoneNumber(dto.getPhoneNumber());
        clientService.updateProfileData(existing);
    }

    @Override
    public void changePassword(ChangePasswordRequestDto dto) {
        Client client = clientService.findByEmail(dto.getEmail());
        if (!client.getEnabled()) {
            throw new EntityNotFoundException("can't find user with this email");
        }
        if (!encoder.matches(dto.getOldPassword(), client.getPassword())) {
            throw new WrongPasswordException("old password is wrong");
        }
        clientService.changePassword(dto.getPassword(), client.getId());
    }

    private Client getClientFromRequestDto(ClientRegisterRequestDto clientRequestDto) {
        Client client = new Client();
        client.setFirstName(clientRequestDto.getFirstName());
        client.setLastName(clientRequestDto.getLastName());
        client.setEmail(clientRequestDto.getEmail());
        client.setPassword(clientRequestDto.getPassword());
        client.setBirthDate(clientRequestDto.getBirthDate());
        client.setPhoneNumber(clientRequestDto.getPhoneNumber());
        client.setSex(clientRequestDto.getSex());
        return client;
    }
}
