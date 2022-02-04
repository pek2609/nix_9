package ua.com.alevel;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.exception.AlreadyExistEntity;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.entity.Bus;
import ua.com.alevel.persistence.entity.Route;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.persistence.type.Town;
import ua.com.alevel.service.bus.BusService;
import ua.com.alevel.service.route.RouteService;
import ua.com.alevel.service.trip.TripService;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class RouteServiceTest {

    @Autowired
    private RouteService routeService;

    @Autowired
    private BusService busService;

    @Autowired
    private TripService tripService;

    private final static int ROUTE_SIZE = 10;

    @BeforeAll
    void init() {
        for (int i = 0; i < ROUTE_SIZE; i++) {
            Route route = GenerationUtil.generateRoute(i);
            routeService.create(route);
            Bus bus = GenerationUtil.generateBus("bus" + i, 25 + i);
            busService.create(bus);
            Trip trip = GenerationUtil.generateTrip(100.0 + i, route, bus);
            tripService.create(trip);
        }
        Assertions.assertEquals(ROUTE_SIZE, routeService.findAll().size());
    }

    @AfterAll
    void clear() {
        tripService.findAll(GenerationUtil.getRequest("departure", 1, ROUTE_SIZE)).getItems().forEach(trip -> routeService.delete(trip.getId()));
        busService.findAll().forEach(bus -> busService.delete(bus.getId()));
        routeService.findAll().forEach(route -> routeService.delete(route.getId()));
    }

    @Order(1)
    @Test
    void shouldNotCreateWithTheSameDepartureArrivalTowns() {
        Exception exception = assertThrows(AlreadyExistEntity.class, () -> {
            routeService.create(GenerationUtil.generateRoute(0));
        });
    }

    @Order(2)
    @Test
    void shouldBeUpdateRoute() {
        Route route = routeService.findAll().stream().findFirst().get();
        route.setDepartureTown(Town.DNIPRO);
        routeService.update(route);
        route = routeService.findById(route.getId());
        Assertions.assertEquals(Town.DNIPRO, route.getDepartureTown());
    }

    @Order(3)
    @Test
    void shouldBeGotExceptionUpdatingRouteNotExist() {
        Route route = GenerationUtil.generateRoute(3);
        route.setDepartureTown(Town.DNIPRO);
        route.setId(0L);
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            routeService.update(route);
        });
    }

    @Order(4)
    @Test
    void shouldBeDeleteRoute() {
        Route route = routeService.findAll().stream().findFirst().get();
        routeService.delete(route.getId());
        Assertions.assertEquals(ROUTE_SIZE - 1, routeService.findAll().size());
    }

    @Order(5)
    @Test
    void shouldBeNotDeleteIfBusNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            routeService.delete(0L);
        });
    }

    @Order(6)
    @Test
    void shouldBeNotFindIfBusNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            routeService.findById(0L);
        });
    }

    @Order(7)
    @Test
    void shouldBeFindAllWithPagination() {
        DataTableRequest request = GenerationUtil.getRequest("arrivalTown", 1, 5);
        Assertions.assertEquals(5, routeService.findAll(request).getItems().size());
    }

    @Order(8)
    @Test
    void shouldFindAllByTripsGroupsByRoute() {
        DataTableRequest request = GenerationUtil.getRequest("price", 1, 5);
        Assertions.assertEquals(5, routeService.findAllByTripsGroupsByRoute(tripService.findAll(request).getItems()).size());
    }
}
