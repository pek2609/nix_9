package ua.com.alevel;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.entity.Bus;
import ua.com.alevel.persistence.entity.Promotion;
import ua.com.alevel.persistence.entity.Route;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.persistence.type.Town;
import ua.com.alevel.service.bus.BusService;
import ua.com.alevel.service.promotion.PromotionService;
import ua.com.alevel.service.route.RouteService;
import ua.com.alevel.service.trip.TripService;
import ua.com.alevel.web.dto.trip.TripSearchRequest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class TripServiceTest {

    @Autowired
    private RouteService routeService;

    @Autowired
    private BusService busService;

    @Autowired
    private TripService tripService;

    @Autowired
    private PromotionService promotionService;


    private final static int TRIP_SIZE = 10;

    @BeforeAll
    void init() {
        for (int i = 0; i < TRIP_SIZE; i++) {
            Route route = GenerationUtil.generateRoute(i);
//            routeService.create(route);
            Bus bus = GenerationUtil.generateBus("bus", 25 + i);
            busService.create(bus);
            Trip trip = GenerationUtil.generateTrip(100.0 + i, route, bus);
            tripService.create(trip);
        }
        Assertions.assertEquals(0, tripService.findAll(GenerationUtil.getRequest("price", 2, TRIP_SIZE)).getItems().size());
        Assertions.assertEquals(TRIP_SIZE, tripService.findAll(GenerationUtil.getRequest("price", 1, TRIP_SIZE)).getItems().size());
    }

    @AfterAll
    void clear() {
        tripService.findAll(GenerationUtil.getRequest("departure", 1, TRIP_SIZE)).getItems().forEach(trip -> routeService.delete(trip.getId()));
        routeService.findAll().forEach(route -> routeService.delete(route.getId()));
        busService.findAll().forEach(bus -> busService.delete(bus.getId()));
    }

    @Order(1)
    @Test
    void shouldUpdateTrip() {
        promotionService.create(GenerationUtil.generatePromotion("name", 10));
        Promotion promotion = promotionService.findAll().stream().findFirst().get();
        Trip trip = tripService.findAll(GenerationUtil.getRequest("departure", 1, TRIP_SIZE)).getItems().stream().findFirst().get();
        trip.setPrice(500.0);
        trip.setPromotion(promotionService.findById(promotion.getId()));
        tripService.update(trip);
        trip = tripService.findById(trip.getId());
        Assertions.assertEquals(500.0, trip.getPrice());
        Assertions.assertEquals(promotion.getId(), trip.getPromotion().getId());
    }

    @Order(2)
    @Test
    void shouldBeGotExceptionUpdatingTripNotExist() {
        Trip trip = tripService.findAll(GenerationUtil.getRequest("departure", 1, TRIP_SIZE)).getItems().stream().findFirst().get();
        trip.setId(0L);
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            tripService.update(trip);
        });
    }

    @Order(3)
    @Test
    void shouldFindAllByBusWithPagination() {
        Bus bus = busService.findAll().stream().findFirst().get();
        System.out.println(bus.getId());
        DataTableRequest dataTableRequest = GenerationUtil.getRequest("bus.name", 1, 3);
        Assertions.assertEquals(1, (tripService.findAllByBus(dataTableRequest, bus.getId()).getItems()).size());
    }

    @Order(4)
    @Test
    void shouldFindAllByPromotionWithPagination() {
        Promotion promotion = promotionService.findAll().stream().findFirst().get();
        DataTableRequest dataTableRequest = GenerationUtil.getRequest("bus.name", 1, 3);
        Assertions.assertEquals(1, tripService.findAllByPromotion(dataTableRequest, promotion.getId()).getItems().size());
    }

    @Order(5)
    @Test
    void shouldFindAllByRouteWithPagination() {
//        Route route = routeService.findAll().stream().findFirst().get();
        DataTableRequest dataTableRequest = GenerationUtil.getRequest("bus.name", 1, 3);
//        Assertions.assertEquals(1, tripService.findAllByRoute(dataTableRequest, route.getId()).getItems().size());
    }

    @Order(6)
    @Test
    void shouldBeDeleteRoute() {
        Trip trip = tripService.findAll(GenerationUtil.getRequest("departure", 1, TRIP_SIZE)).getItems().stream().findFirst().get();

        tripService.delete(trip.getId());
        Assertions.assertEquals(0, tripService.findAll(GenerationUtil.getRequest("price", 2, TRIP_SIZE - 1)).getItems().size());
        Assertions.assertEquals(TRIP_SIZE - 1, tripService.findAll(GenerationUtil.getRequest("price", 1, TRIP_SIZE - 1)).getItems().size());
    }

    @Order(7)
    @Test
    void shouldBeNotDeleteIfBusNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            tripService.delete(0L);
        });
    }

    @Order(8)
    @Test
    void shouldBeNotFindIfBusNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            tripService.findById(0L);
        });
    }

    @Order(9)
    @Test
    void shouldBeFindAllWithPagination() {
        DataTableRequest dataTableRequest = GenerationUtil.getRequest("bus.name", 1, 3);
        Assertions.assertEquals(3, tripService.findAll(dataTableRequest).getItems().size());
    }

    @Order(10)
    @Test
    void shouldFindAllBySearch() {
        Trip trip = tripService.findAll(GenerationUtil.getRequest("departure", 1, TRIP_SIZE)).getItems().stream().findFirst().get();
        TripSearchRequest tripSearchRequest = new TripSearchRequest();
        tripSearchRequest.setDepartureTown(trip.getRoute().getDepartureTown());
        tripSearchRequest.setArrivalTown(trip.getRoute().getArrivalTown());
        tripSearchRequest.setChildren(0);
        tripSearchRequest.setAdults(0);
        tripSearchRequest.setDeparture(new Date());
        Assertions.assertEquals(1, tripService.findAllBySearch(tripSearchRequest).size());
    }
}
