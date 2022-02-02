package ua.com.alevel.service.client.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.AlreadyExistEntity;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.persistence.repository.user.ClientRepository;
import ua.com.alevel.service.client.ClientService;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ClientRepository personalRepository;
    private final CrudRepositoryHelper<Client, ClientRepository> crudRepositoryHelper;

    public ClientServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, ClientRepository personalRepository, CrudRepositoryHelper<Client, ClientRepository> crudRepositoryHelper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.personalRepository = personalRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(Client entity) {
        if (personalRepository.existsByEmail(entity.getEmail())) {
            throw new AlreadyExistEntity("client with this email is exist");
        }
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        crudRepositoryHelper.create(personalRepository, entity);
    }

    @Override
    @Transactional
    public void update(Client entity) {
        crudRepositoryHelper.update(personalRepository, entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        crudRepositoryHelper.delete(personalRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Long id) {
        Optional<Client> client = crudRepositoryHelper.findById(personalRepository, id);
        if (client.isEmpty()) {
            throw new EntityNotFoundException("client is not found");
        }
        return client.get();
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Client> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(personalRepository, request);
    }

    @Transactional
    @Override
    public void ban(Long clientId) {
        personalRepository.ban(clientId);
    }

    @Transactional
    @Override
    public void unban(Long clientId) {
        personalRepository.unban(clientId);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return personalRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Client findByEmail(String email) {
        return personalRepository.findByEmail(email);
    }

    @Override
    public List<Client> findAll() {
        return personalRepository.findAll();
    }
}
