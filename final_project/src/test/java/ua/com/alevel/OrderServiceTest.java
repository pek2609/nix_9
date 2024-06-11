//package ua.com.alevel;
//
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import ua.com.alevel.exception.EntityNotFoundException;
//import ua.com.alevel.persistence.datatable.DataTableRequest;
//import ua.com.alevel.persistence.entity.Bus;
//import ua.com.alevel.persistence.entity.Order;
//import ua.com.alevel.persistence.entity.Route;
//import ua.com.alevel.persistence.entity.Trip;
//import ua.com.alevel.persistence.entity.user.Client;
//import ua.com.alevel.service.bus.BusService;
//import ua.com.alevel.service.client.ClientService;
//import ua.com.alevel.service.order.OrderService;
//import ua.com.alevel.service.route.RouteService;
//import ua.com.alevel.service.trip.TripService;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest
//public class OrderServiceTest {
//
//    @Autowired
//    private ClientService clientService;
//
//    @Autowired
//    private TripService tripService;
//
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private RouteService routeService;
//
//    @Autowired
//    private BusService busService;
//
//    private final static int ORDERS_SIZE = 10;
//
//    @BeforeAll
//    void init() {
//        for (int i = 0; i < ORDERS_SIZE; i++) {
//            Route route = GenerationUtil.generateRoute(i);
//            routeService.create(route);
//            Bus bus = GenerationUtil.generateBus("bus", 25 + i);
//            busService.create(bus);
//            Trip trip = GenerationUtil.generateTrip(100.0 + i, route, bus);
//            tripService.create(trip);
//            Order order = GenerationUtil.generateOrder("order", trip);
//            orderService.create(order);
//        }
//        Assertions.assertEquals(0, orderService.findAll(GenerationUtil.getRequest("finalPrice", 2, ORDERS_SIZE)).getItems().size());
//        Assertions.assertEquals(ORDERS_SIZE, orderService.findAll(GenerationUtil.getRequest("finalPrice", 1, ORDERS_SIZE)).getItems().size());
//    }
//
//    @AfterAll
//    void clearRoutes() {
//        orderService.findAll(GenerationUtil.getRequest("finalPrice", 1, ORDERS_SIZE)).getItems().forEach(trip -> orderService.delete(trip.getId()));
//        tripService.findAll(GenerationUtil.getRequest("departure", 1, ORDERS_SIZE)).getItems().forEach(trip -> tripService.delete(trip.getId()));
//        routeService.findAll().forEach(route -> routeService.delete(route.getId()));
//        busService.findAll().forEach(route -> busService.delete(route.getId()));
//        clientService.findAll().forEach(route -> clientService.delete(route.getId()));
//    }
//
//
//    @org.junit.jupiter.api.Order(1)
//    @Test
//    void shouldBeUpdateOrder() {
//        Order order = orderService.findAll(GenerationUtil.getRequest("finalPrice", 1, ORDERS_SIZE)).getItems().stream().findFirst().get();
//        order.setFinalPrice(450.0);
//        Client client = GenerationUtil.generateClient("client");
//        clientService.create(client);
//        order.setClient(client);
//        orderService.update(order);
//        client = clientService.findAll().stream().findFirst().get();
//        Assertions.assertEquals(450.0, order.getFinalPrice());
//        Assertions.assertEquals(client.getId(), order.getClient().getId());
//    }
//
//    @org.junit.jupiter.api.Order(2)
//    @Test
//    void shouldBeGotExceptionUpdatingOrderNotExist() {
//        Order order = orderService.findAll(GenerationUtil.getRequest("finalPrice", 1, ORDERS_SIZE)).getItems().stream().findFirst().get();
//        order.setId(0L);
//        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
//            orderService.update(order);
//        });
//    }
//
//    @org.junit.jupiter.api.Order(3)
//    @Test
//    void shouldBeDeleteOrder() {
//        Order order = orderService.findAll(GenerationUtil.getRequest("finalPrice", 1, ORDERS_SIZE)).getItems().stream().findFirst().get();
//        orderService.delete(order.getId());
//        Assertions.assertEquals(0, orderService.findAll(GenerationUtil.getRequest("finalPrice", 2, ORDERS_SIZE - 1)).getItems().size());
//        Assertions.assertEquals(ORDERS_SIZE - 1, orderService.findAll(GenerationUtil.getRequest("finalPrice", 1, ORDERS_SIZE - 1)).getItems().size());
//    }
//
//    @org.junit.jupiter.api.Order(4)
//    @Test
//    void shouldBeNotDeleteIfOrderNotExist() {
//        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
//            orderService.delete(0L);
//        });
//    }
//
//    @org.junit.jupiter.api.Order(5)
//    @Test
//    void shouldNotFindIfOrderNotExist() {
//        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
//            orderService.findById(0L);
//        });
//    }
//
//    @org.junit.jupiter.api.Order(6)
//    @Test
//    void shouldFindAllWithPagination() {
//        DataTableRequest dataTableRequest = GenerationUtil.getRequest("finalPrice", 1, 3);
//        Assertions.assertEquals(3, orderService.findAll(dataTableRequest).getItems().size());
//    }
//
//    @org.junit.jupiter.api.Order(6)
//    @Test
//    void shouldFindAllByClientWithPagination() {
//        Client client = clientService.findAll().stream().findFirst().get();
//        DataTableRequest dataTableRequest = GenerationUtil.getRequest("finalPrice", 1, 10);
//        Assertions.assertEquals(1, orderService.findByClient(client.getId(), dataTableRequest).getItems().size());
//    }
//
//    @org.junit.jupiter.api.Order(6)
//    @Test
//    void shouldFindAllByTripWithPagination() {
//        Trip trip = tripService.findAll(GenerationUtil.getRequest("departure", 1, ORDERS_SIZE)).getItems().stream().findFirst().get();
//        DataTableRequest dataTableRequest = GenerationUtil.getRequest("finalPrice", 1, 3);
//        Assertions.assertEquals(1, orderService.findByTrip(trip.getId(), dataTableRequest).getItems().size());
//    }
//
//
//}
