package ua.com.alevel.web.controller.open;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.alevel.persistence.entity.Booking;
import ua.com.alevel.service.booking.BookingService;
import ua.com.alevel.service.trip.v2.SearchTripResult;
import ua.com.alevel.service.trip.v2.TripServiceV2;
import ua.com.alevel.web.dto.BookingRequestDto;
import ua.com.alevel.web.dto.PassengerRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/open/booking")
@AllArgsConstructor
public class BookingController {

    private TripServiceV2 tripService;
    private BookingService bookingService;

    @PostMapping
    public String prepareBooking(Model model,
                                 @NotNull @RequestParam("tripId") Long tripId,
                                 @NotNull @RequestParam("adults") Integer adults,
                                 @NotNull @RequestParam("children") Integer children) {
        SearchTripResult searchTripResult = tripService.prepareTripSearchResult(tripId, adults, children);
        BookingRequestDto bookingRequestDto = new BookingRequestDto();
        bookingRequestDto.setTotalPrice(searchTripResult.getTotalPrice());
        bookingRequestDto.setTripId(tripId);
        bookingRequestDto.setAdults(adults);
        bookingRequestDto.setChildren(children);
        List<PassengerRequestDto> passengers = new ArrayList<>();
        for (int i = 0; i < adults + children; i++) {
            PassengerRequestDto preq = new PassengerRequestDto();
            passengers.add(preq);
        }
        bookingRequestDto.setPassengers(passengers);

        model.addAttribute("booking", bookingRequestDto);
        model.addAttribute("trip", searchTripResult);
        return "pages/open/open_booking_new";
    }


    @PostMapping("/new")
    public String createBooking(Model model, @Valid @ModelAttribute("booking") BookingRequestDto bookingRequestDto) {
        System.out.println(bookingRequestDto);
        Booking booking = bookingService.createBooking(bookingRequestDto);
        model.addAttribute("booking", bookingRequestDto);
        model.addAttribute("trip", tripService.prepareTripSearchResult(bookingRequestDto.getTripId(), bookingRequestDto.getAdults(), bookingRequestDto.getChildren()));
        return "pages/open/open_booking_confirm";
    }
}
