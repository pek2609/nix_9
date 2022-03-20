package ua.com.alevel;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.entity.Bus;
import ua.com.alevel.service.bus.BusService;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class BusServiceTest {

    @Autowired
    private BusService busService;

    private final static int BUS_SIZE = 10;

    @BeforeAll
    void init() {
        for (int i = 0; i < BUS_SIZE; i++) {
            Bus bus = GenerationUtil.generateBus("bus" + i , 20 + i);
            busService.create(bus);
        }
        Assertions.assertEquals(BUS_SIZE, busService.findAll().size());
    }

    @AfterAll
    void clear(){
        busService.findAll().forEach(bus->busService.delete(bus.getId()));
    }

    @Order(1)
    @Test
    void shouldBeUpdateBus() {
        Bus foundBus = busService.findAll().stream().findFirst().get();
        foundBus.setName("updateName");
        foundBus.setImageUrl("updateImage.jpg");
        foundBus.setSeats(25);
        busService.update(foundBus);
        foundBus = busService.findById(foundBus.getId());
        Assertions.assertEquals("updateName", foundBus.getName());
        Assertions.assertEquals("updateImage.jpg", foundBus.getImageUrl());
        Assertions.assertEquals(25, foundBus.getSeats());
    }

    @Order(2)
    @Test
    void shouldBeGotExceptionUpdatingBusNotExist() {
        Bus bus = GenerationUtil.generateBus("bus", 0);
        bus.setId(0L);
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            busService.update(bus);
        });
    }

    @Order(3)
    @Test
    void shouldBeDeleteBus() {
        Bus foundBus = busService.findAll().stream().findFirst().get();
        busService.delete(foundBus.getId());
        Assertions.assertEquals(BUS_SIZE-1 , busService.findAll().size());
    }

    @Order(4)
    @Test
    void shouldBeNotDeleteIfBusNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            busService.delete(0L);
        });
    }

    @Order(5)
    @Test
    void shouldBeNotFindIfBusNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            busService.findById(0L);
        });
    }

    @Order(6)
    @Test
    void shouldBeFindAllWithPagination() {
        DataTableRequest dataTableRequest = GenerationUtil.getRequest("name", 1, 3);
        Assertions.assertEquals(3, busService.findAll(dataTableRequest).getItems().size());
    }
}
