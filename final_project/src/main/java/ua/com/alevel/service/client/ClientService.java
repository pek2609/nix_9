package ua.com.alevel.service.client;

import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.service.BaseService;

import java.util.List;

public interface ClientService extends BaseService<Client> {

    void ban(Long clientId);

    void unban(Long clientId);

    boolean existsByPhoneNumber(String phoneNumber);

    void updateProfileData(Client client);

    void changePassword(String password, Long clientId);

    Client findByEmail(String email);

    List<Client> findAll();
}
