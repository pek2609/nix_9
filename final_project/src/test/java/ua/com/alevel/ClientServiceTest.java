package ua.com.alevel;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.alevel.exception.AlreadyExistEntity;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.service.client.ClientService;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private final static int CLIENTS_SIZE = 10;

    @BeforeAll
    void init() {
        for (int i = 0; i < CLIENTS_SIZE; i++) {
            Client Client = GenerationUtil.generateClient("name" + i);
            clientService.create(Client);
        }
        Assertions.assertEquals(CLIENTS_SIZE, clientService.findAll().size());
    }

    @AfterAll
    void clear() {
        clientService.findAll().forEach(client -> clientService.delete(client.getId()));
    }


    @Order(1)
    @Test
    void shouldNotCreateClientWithTheSameEmail() {
        Client client = GenerationUtil.generateClient("name1");
        Exception exception = assertThrows(AlreadyExistEntity.class, () -> {
            clientService.create(client);
        });
    }

    @Order(2)
    @Test
    void shouldUpdateClient() {
        Client foundClient = clientService.findAll().stream().findFirst().get();
        Client client = GenerationUtil.generateClient("update");
        client.setId(foundClient.getId());
        clientService.update(client);
        foundClient = clientService.findById(foundClient.getId());
        Assertions.assertEquals(client.getFirstName(), foundClient.getFirstName());
        Assertions.assertEquals(client.getLastName(), foundClient.getLastName());
        Assertions.assertEquals(client.getPhoneNumber(), foundClient.getPhoneNumber());
        Assertions.assertTrue(encoder.matches("update", foundClient.getPassword()));
        Assertions.assertEquals(client.getEmail(), foundClient.getEmail());
    }

    @Order(3)
    @Test
    void shouldUpdateProfileData() {
        Client foundClient = clientService.findAll().stream().findFirst().get();
        Client client = GenerationUtil.generateClient("updateProfile");
        client.setId(foundClient.getId());
        clientService.updateProfileData(client);
        foundClient = clientService.findById(foundClient.getId());
        Assertions.assertEquals(client.getFirstName(), foundClient.getFirstName());
        Assertions.assertEquals(client.getLastName(), foundClient.getLastName());
        Assertions.assertEquals(client.getPhoneNumber(), foundClient.getPhoneNumber());
        Assertions.assertEquals(client.getEmail(), foundClient.getEmail());
    }

    @Order(3)
    @Test
    void shouldNotUpdateClientWithTheSameEmail() {
        Client foundClient = clientService.findAll().stream().findFirst().get();
        Client client = GenerationUtil.generateClient("name7");
        client.setId(foundClient.getId());
        Exception exception = assertThrows(AlreadyExistEntity.class, () -> {
            clientService.create(client);
        });
    }

    @Order(4)
    @Test
    void shouldBeGotExceptionUpdatingClientNotExist() {
        Client client = GenerationUtil.generateClient("client");
        client.setId(0L);
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            clientService.update(client);
        });
    }

    @Order(5)
    @Test
    void shouldBeDeleteClient() {
        Client foundClient = clientService.findAll().stream().findFirst().get();
        clientService.delete(foundClient.getId());
        Assertions.assertEquals(CLIENTS_SIZE - 1, clientService.findAll().size());
    }

    @Order(6)
    @Test
    void shouldBeNotDeleteIfClientNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            clientService.delete(0L);
        });
    }

    @Order(7)
    @Test
    void shouldBeNotFindIfClientNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            clientService.findById(0L);
        });
    }

    @Order(8)
    @Test
    void shouldBeFindAllWithPagination() {
        DataTableRequest dataTableRequest = GenerationUtil.getRequest("firstName", 1, 3);
        Assertions.assertEquals(3, clientService.findAll(dataTableRequest).getItems().size());
    }

    @Order(9)
    @Test
    void shouldBanOrThrowExceptionIfClientNotFound() {
        Client foundClient = clientService.findAll().stream().findFirst().get();
        clientService.ban(foundClient.getId());
        Assertions.assertFalse(clientService.findById(foundClient.getId()).getEnabled());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            clientService.ban(1L);
        });
    }

    @Order(10)
    @Test
    void shouldUnbanOrThrowExceptionIfClientNotFound() {
        Client foundClient = clientService.findAll().stream().findFirst().get();
        clientService.unban(foundClient.getId());
        Assertions.assertTrue(clientService.findById(foundClient.getId()).getEnabled());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            clientService.ban(1L);
        });
    }

    @Order(11)
    @Test
    void shouldChangePassword() {
        Client foundClient = clientService.findAll().stream().findFirst().get();
        clientService.changePassword("newpass", foundClient.getId());
        Assertions.assertTrue(encoder.matches("newpass", clientService.findById(foundClient.getId()).getPassword()));
    }
}
