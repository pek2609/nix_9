package ua.com.alevel.persistence.repository.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends UserRepository<Client> {

    @Modifying
    @Query(value = "update Client set enabled = false where id=:id")
    void ban(@Param("id") Long clientId);

    @Modifying
    @Query(value = "update Client set enabled = true where id=:id")
    void unban(@Param("id") Long clientId);

    boolean existsByPhoneNumber(String phoneNumber);
}
