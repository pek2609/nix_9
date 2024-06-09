package ua.com.alevel.filter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BookingStatus;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class BookingFilter {

    private String uuid;
    private LocalDate from;
    private LocalDate to;
    private boolean fetchHistory;
    private Long clientId;
    private BookingStatus status;
}
