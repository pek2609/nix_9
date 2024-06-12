package ua.com.alevel.web.controller.driver;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.alevel.persistence.entity.Booking;
import ua.com.alevel.persistence.entity.TripStatus;
import ua.com.alevel.persistence.entity.TripV2;
import ua.com.alevel.service.booking.BookingService;
import ua.com.alevel.service.trip.v2.TripServiceV2;
import ua.com.alevel.service.user.DriverService;
import ua.com.alevel.validated.annotation.ValidId;

import java.util.List;

@Validated
@Controller
@AllArgsConstructor
@RequestMapping("/driver/trips")
public class DriverTripController {

    private final TripServiceV2 tripService;
    private final DriverService driverService;
    private final BookingService bookingService;

    @GetMapping
    public String findDriverTrips(Model model) {
        List<TripV2> driverTrips = tripService.getDriverTrips(driverService.getCurrentDriver());

        model.addAttribute("driverTrips", driverTrips);
        return "pages/driver/trips";
    }

    @GetMapping("/{id}/bookings")
    public String getTripBookings(@PathVariable @ValidId Long id, @RequestParam(value = "searchPhone", required = false) String searchPhone, Model model) {
        TripV2 byId = tripService.findById(id);
        List<Booking> bookings = bookingService.getBookingsByTrip(id, searchPhone);
        model.addAttribute("tripBookings", bookings);
        model.addAttribute("searchPhone", searchPhone);
        model.addAttribute("showPresenceCheckBox", byId.getTripStatus() == TripStatus.ON_THE_WAY);
        return "pages/driver/trip_bookings";
    }
}
