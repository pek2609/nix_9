package ua.com.alevel.service.user.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.exception.AlreadyExistEntity;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.persistence.repository.user.ClientRepository;
import ua.com.alevel.persistence.type.Role;
import ua.com.alevel.service.user.ClientService;
import ua.com.alevel.util.Messages;
import ua.com.alevel.util.SecurityUtil;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {


    private final LoggerService loggerService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ClientRepository personalRepository;
    private final CrudRepositoryHelper<Client, ClientRepository> crudRepositoryHelper;
    private final SecurityService securityService;

    @Override
    @Transactional
    public void create(Client entity) {
        checkExistByEmailAndPhone(entity.getEmail(), entity.getPhoneNumber(), entity.getId());
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        crudRepositoryHelper.create(personalRepository, entity);
    }

    @Override
    @Transactional
    public void update(Client entity) {
        checkExistByEmailAndPhone(entity.getEmail(), entity.getPhoneNumber(), entity.getId());
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        crudRepositoryHelper.update(personalRepository, entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, Messages.entityLog("delete", "client", id, "start"));
        crudRepositoryHelper.delete(personalRepository, id);
        loggerService.commit(LoggerLevel.WARN, Messages.entityLog("delete", "client", id, "end"));
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Long id) {
        Optional<Client> client = crudRepositoryHelper.findById(personalRepository, id);
        if (client.isEmpty()) {
            loggerService.commit(LoggerLevel.ERROR, Messages.entityNotFoundLog("client", id));
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
        loggerService.commit(LoggerLevel.INFO, Messages.entityLog("ban", "client", "start"));
        if (!personalRepository.existsById(clientId)) {
            loggerService.commit(LoggerLevel.ERROR, Messages.entityNotFoundLog("client", clientId));
            throw new EntityNotFoundException("client is not found");
        }
        personalRepository.ban(clientId);
        loggerService.commit(LoggerLevel.INFO, Messages.entityLog("ban", "client", "end"));
    }

    @Transactional
    @Override
    public void unban(Long clientId) {
        loggerService.commit(LoggerLevel.INFO, Messages.entityLog("unban", "client", "start"));
        if (!personalRepository.existsById(clientId)) {
            loggerService.commit(LoggerLevel.ERROR, Messages.entityNotFoundLog("client", clientId));
            throw new EntityNotFoundException("client is not found");
        }
        personalRepository.unban(clientId);
        loggerService.commit(LoggerLevel.INFO, Messages.entityLog("unban", "client", "start"));
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return personalRepository.existsByPhoneNumber(phoneNumber);
    }

    @Transactional
    @Override
    public void updateProfileData(Client client) {
        loggerService.commit(LoggerLevel.INFO, Messages.entityLog("updateProfileData", "client", client.getId(), "start"));
        if (!personalRepository.existsById(client.getId())) {
            loggerService.commit(LoggerLevel.ERROR, Messages.entityNotFoundLog("client", client.getId()));
            throw new EntityNotFoundException("client is not found");
        }
        checkExistByEmailAndPhone(client.getEmail(), client.getPhoneNumber(), client.getId());
        personalRepository.updateProfileData(client);
        loggerService.commit(LoggerLevel.INFO, Messages.entityLog("updateProfileData", "client", client.getId(), "end"));
    }

    @Transactional
    @Override
    public void changePassword(String password, Long clientId) {
        loggerService.commit(LoggerLevel.INFO, Messages.entityLog("changePassword", "client", clientId, "start"));
        if (!personalRepository.existsById(clientId)) {
            loggerService.commit(LoggerLevel.ERROR, Messages.entityNotFoundLog("client", clientId));
            throw new EntityNotFoundException("client is not found");
        }
        personalRepository.changePassword(bCryptPasswordEncoder.encode(password), clientId);
        loggerService.commit(LoggerLevel.INFO, Messages.entityLog("changePassword", "client", clientId, "end"));
    }

    @Transactional(readOnly = true)
    @Override
    public Client findByEmail(String email) {
        return personalRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Client> findAll() {
        return crudRepositoryHelper.findAll(personalRepository);
    }

    @Transactional
    @Override
    public Client getCurrentClient() {
        if (!SecurityUtil.hasRole(Role.ROLE_CLIENT.name())) {
            System.out.println("CURRENT USER IS NOT CLIENT");
        }
        return findByEmail(securityService.getCurrentUserName());
    }

    private void checkExistByEmailAndPhone(String email, String phone, Long id) {
        if (personalRepository.existsClientByEmailAndIdNot(email, id)) {
            loggerService.commit(LoggerLevel.ERROR, "client exist with email , id = " + id);
            throw new AlreadyExistEntity("client with this email is exist");
        }
        if (personalRepository.existsClientByPhoneNumberAndIdNot(phone, id)) {
            loggerService.commit(LoggerLevel.ERROR, "client exist with phone , id = " + id);
            throw new AlreadyExistEntity("user with such phoneNumber is already exist");
        }
    }
}
