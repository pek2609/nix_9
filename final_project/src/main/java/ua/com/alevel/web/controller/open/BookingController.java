package ua.com.alevel.web.controller.open;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.entity.Booking;
import ua.com.alevel.persistence.entity.BookingStatus;
import ua.com.alevel.service.booking.BookingService;
import ua.com.alevel.service.trip.v2.SearchTripResult;
import ua.com.alevel.service.trip.v2.TripServiceV2;
import ua.com.alevel.web.dto.BookingRequestDto;
import ua.com.alevel.web.dto.BookingSearchDto;
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

        model.addAttribute("booking", booking);
        model.addAttribute("bookingId", booking.getId());
        model.addAttribute("trip", tripService.prepareTripSearchResult(booking.getTrip().getId(), booking.getAdults(), booking.getChildren()));

        return "pages/open/open_booking_confirm";
//        return "redirect:/open/booking/confirm" + booking.getId();
    }

    @GetMapping("/search")
    public String prepareSearchBooking(Model model) {
        model.addAttribute("search", new BookingSearchDto(null, null));
        return "pages/open/open_booking_search";
    }

    @PostMapping("/search")
    public String searchByUUID(Model model, @ModelAttribute("search") BookingSearchDto bookingSearchDto) {
        Booking booking = bookingService.searchBooking(bookingSearchDto.uuid(), bookingSearchDto.email());
        if (booking != null) {
            model.addAttribute("booking", booking);
        }
        model.addAttribute("searchPerformed", true);
        model.addAttribute("trip", SearchTripResult.from(booking.getTrip(), booking.getAdults(), booking.getChildren()));

        return "pages/open/open_booking_search";
    }


    @GetMapping("/confirm/{id}")
    public String prepareConfirmation(Model model, @PathVariable("id") Long bookingId) {
        Booking booking = bookingService.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking Not Found"));
        model.addAttribute("booking", booking);
        model.addAttribute("bookingId", booking.getId());
        model.addAttribute("trip", tripService.prepareTripSearchResult(booking.getTrip().getId(), booking.getAdults(), booking.getChildren()));

        return "pages/open/open_booking_confirm";
    }


    @PostMapping("/confirm/{id}")
    public String confirmBooking(Model model, @PathVariable("id") Long bookingId) {
        Booking booking = bookingService.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking Not Found"));

        if (booking.getStatus() == BookingStatus.CONFIRMED || booking.getStatus() == BookingStatus.NEW) {
            model.addAttribute("message", "You confirmed your booking successfully");
        }
        if (booking.getStatus() == BookingStatus.CANCELED) {
            model.addAttribute("message", "Your booking is already canceled");
        }
        bookingService.confirmBooking(booking);

        bookingService.sendBookingConfirmedNotification(booking);

        return "pages/open/open_process_booking";
    }

    @PostMapping("/cancel/{id}")
    public String cancelBooking(Model model, @PathVariable("id") Long bookingId) {
        Booking booking = bookingService.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking Not Found"));

        if (booking.getStatus() == BookingStatus.CONFIRMED || booking.getStatus() == BookingStatus.NEW) {
            model.addAttribute("message", "You canceled your booking successfully");
        }
        if (booking.getStatus() == BookingStatus.CANCELED) {
            model.addAttribute("message", "Your booking is already canceled");
        }

        bookingService.cancelBooking(booking);

        bookingService.sendBookingCanceledNotification(booking);

        return "pages/open/open_process_booking";

//        return "redirect:/open/tickets";
    }
}
