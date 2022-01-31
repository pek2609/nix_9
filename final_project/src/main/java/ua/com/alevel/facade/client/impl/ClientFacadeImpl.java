package ua.com.alevel.facade.client.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.exception.AlreadyExistEntity;
import ua.com.alevel.facade.client.ClientFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.service.client.ClientService;
import ua.com.alevel.util.FacadeUtil;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.web.dto.client.ClientRequestDto;
import ua.com.alevel.web.dto.client.ClientResponseDto;
import ua.com.alevel.web.dto.datatable.PageAndSizeData;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.datatable.SortData;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientFacadeImpl implements ClientFacade {

    private final ClientService clientService;

    public ClientFacadeImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void create(ClientRequestDto clientRequestDto) {
        if (clientService.existsByPhoneNumber(clientRequestDto.getPhoneNumber())) {
            throw new AlreadyExistEntity("user with such phoneNumber is already exist");
        }
        Client client = getClientFromRequestDto(clientRequestDto);
        clientService.create(client);
    }

    @Override
    public void update(ClientRequestDto clientRequestDto, Long id) {
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

    private Client getClientFromRequestDto(ClientRequestDto clientRequestDto) {
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
