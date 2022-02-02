package ua.com.alevel.service.client;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.service.BaseService;

import java.util.List;

public interface ClientService extends BaseService<Client> {

    void ban(Long clientId);
    void unban(Long clientId);
    boolean existsByPhoneNumber(String phoneNumber);
    Client findByEmail(String email);
    List<Client> findAll();
}
