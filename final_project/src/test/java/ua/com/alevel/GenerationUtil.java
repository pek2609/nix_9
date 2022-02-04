package ua.com.alevel;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.entity.*;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.persistence.type.Sex;
import ua.com.alevel.persistence.type.Town;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class GenerationUtil {

    private GenerationUtil() {
    }

    public static Map<Integer, Town> map = new HashMap<>();

    static {
        map.put(0, Town.KIEV);
        map.put(1, Town.KHARKIV);
        map.put(2, Town.ZAPOROZHYE);
        map.put(3, Town.UZHGOROD);
        map.put(4, Town.ZHYTOMYR);
        map.put(5, Town.KHERSON);
        map.put(6, Town.SUMI);
        map.put(7, Town.IVANO_FRANKIVSK);
        map.put(8, Town.LUTSK);
        map.put(9, Town.LVIV);
        map.put(10, Town.TERNOPIL);
    }

    public static Bus generateBus(String name, int seats) {
        Bus bus = new Bus();
        bus.setName(name);
        bus.setImageUrl(name);
        bus.setSeats(seats);
        return bus;
    }

    public static Client generateClient(String name) {
        Client client = new Client();
        client.setSex(Sex.MALE);
        client.setFirstName(name);
        client.setLastName(name);
        client.setPhoneNumber(name);
        client.setPassword(name);
        client.setEmail(name);
        return client;
    }

    public static Route generateRoute(int i) {
        Route route = new Route();
        route.setDepartureTown(map.get(i));
        route.setArrivalTown(map.get(i + 1));
        return route;
    }

    public static Trip generateTrip(double price, Route route, Bus bus) {
        Trip trip = new Trip();
        trip.setPromotion(null);
        Date date = new Date();
        trip.setDeparture(addDay(date, 1));
        trip.setArrival(addDay(date, 2));
        trip.setBus(bus);
        trip.setPrice(price);
        trip.setRoute(route);
        return trip;
    }

    public static Promotion generatePromotion(String name, int percent) {
        Promotion promotion = new Promotion();
        Date date = new Date();
        promotion.setStart(date);
        promotion.setEnd(addDay(date, 30));
        promotion.setName(name);
        promotion.setImage(name);
        promotion.setPercent(percent);
        return promotion;
    }

    public static Order generateOrder(String name, Trip trip) {
        Order order = new Order();
        order.setFirstName(name);
        order.setLastName(name);
        order.setPhoneNumber(name);
        order.setClient(null);
        order.setAdultsNumber(1);
        order.setChildrenNumber(0);
        order.setFinalPrice(300.0);
        order.setTrip(trip);
        return order;
    }

    public static DataTableRequest getRequest(String sort, int page, int size) {
        DataTableRequest request = new DataTableRequest();
        request.setOrder("asc");
        request.setSort(sort);
        request.setPage(page);
        request.setSize(size);
        return request;
    }

    public static Date addDay(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, amount);
        return c.getTime();
    }

}
