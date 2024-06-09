package ua.com.alevel.web.controller.client;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.filter.BookingFilter;
import ua.com.alevel.persistence.entity.Booking;
import ua.com.alevel.persistence.entity.BookingStatus;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.service.booking.BookingService;
import ua.com.alevel.service.client.ClientService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
@RequestMapping("/client/booking")
public class ClientBookingController {

    private final ClientService clientService;
    private final BookingService bookingService;

    @GetMapping
    public String getBookingHistory(Model model, @RequestParam(name = "uuid", required = false) String uuid,
                                    @RequestParam(name = "from", required = false) LocalDate from, @RequestParam(name = "to", required = false) LocalDate to,
                                    @RequestParam(value = "history", required = false) boolean fetchHistory,
                                    @PageableDefault(size = 5) Pageable pageable) {

        Client currentClient = clientService.getCurrentClient();

        BookingFilter filter = BookingFilter.builder().clientId(currentClient.getId())
                .uuid(uuid)
                .from(from)
                .to(to)
                .fetchHistory(fetchHistory)
                .build();

        Page<Booking> page = bookingService.findAllByFilter(filter, pageable);

        model.addAttribute("page", page);
        model.addAttribute("uuid", uuid);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("to", to);
        model.addAttribute("fetchHistory", fetchHistory);
        return "pages/client/client_bookings";
    }

    @GetMapping("/{id}")
    public String bookingDetails(Model model, @PathVariable("id") Long bookingId) {
        Booking booking = bookingService.findById(bookingId).orElseThrow(() -> new EntityNotFoundException("booking not found"));


        boolean showCancelButton = booking.getStatus() != BookingStatus.CANCELED && LocalDateTime.now().isBefore(booking.getTrip().getDeparture());

        model.addAttribute("booking", booking);
        model.addAttribute("showCancel", showCancelButton);
        return "pages/client/client_booking_detail";
    }
}
