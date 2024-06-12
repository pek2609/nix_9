package ua.com.alevel.service.trip.v2;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Booking;
import ua.com.alevel.persistence.entity.BookingStatus;
import ua.com.alevel.persistence.entity.Passenger;
import ua.com.alevel.persistence.entity.TripStatus;
import ua.com.alevel.persistence.entity.TripV2;
import ua.com.alevel.persistence.entity.user.Driver;
import ua.com.alevel.persistence.repository.TripRepositoryV2;
import ua.com.alevel.service.booking.BookingService;
import ua.com.alevel.util.PriceAndDateUtil;
import ua.com.alevel.web.dto.trip.TripResponseDto;
import ua.com.alevel.web.dto.trip.TripSearchRequest;
import ua.com.alevel.web.dto.trip.TripStatisticsDto;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class TripServiceV2Impl implements TripServiceV2 {

    private @Autowired TripRepositoryV2 tripRepository;
    private @Autowired
    @Lazy BookingService bookingService;
    private @Autowired CrudRepositoryHelper<TripV2, TripRepositoryV2> crudRepositoryHelper;

    @Override
    public List<SearchTripResult> searchTrips(TripSearchRequest request) {
        List<TripV2> bySearch = tripRepository.findBySearch(request.getDepartureTownId(), request.getArrivalTownId(), Date.valueOf(request.getDepartureDate()), request.getAdults() + request.getChildren());

        return bySearch.stream().filter(t -> t.getTripStatus() == TripStatus.PLANNED).map(t -> SearchTripResult.from(t, request.getAdults(), request.getChildren()))
                .collect(Collectors.toList());
    }

    @Override
    public SearchTripResult prepareTripSearchResult(Long tripId, Integer adults, Integer children) {
        return SearchTripResult.from(findById(tripId), adults, children);
    }

    @Override
    public List<TripV2> getDriverTrips(Driver driver) {
        if (driver == null) {
            return Collections.emptyList();
        }
        return tripRepository.getTripV2ByDriversContains(driver).stream()
                .filter(tripV2 -> tripV2.getTripStatus() != TripStatus.COMPLETED)
                .collect(Collectors.toList());
    }

    @Override
    public TripStatisticsDto getTripStatistics(Long tripId) {
        TripV2 trip = tripRepository.findById(tripId).orElse(null);
        if (trip == null) {
            return null;
        }

        List<Booking> tripBookings = bookingService.getBookingsByTrip(tripId, null);
        int totalBookings = tripBookings.size();

        // Booking status counts
        int confirmedCount = (int) tripBookings.stream().filter(b -> b.getStatus() == BookingStatus.CONFIRMED).count();
        int newCount = (int) tripBookings.stream().filter(b -> b.getStatus() == BookingStatus.NEW).count();
        int canceledCount = (int) tripBookings.stream().filter(b -> b.getStatus() == BookingStatus.CANCELED).count();

        // Confirmed bookings only for passenger statistics
        List<Passenger> confirmedPassengers = tripBookings.stream()
                .filter(b -> b.getStatus() == BookingStatus.CONFIRMED)
                .flatMap(booking -> booking.getPassengers().stream())
                .collect(Collectors.toList());

        int totalPassengers = confirmedPassengers.size();

        int totalAdults = tripBookings.stream()
                .filter(b -> b.getStatus() == BookingStatus.CONFIRMED)
                .mapToInt(Booking::getAdults)
                .sum();

        int totalChildren = tripBookings.stream()
                .filter(b -> b.getStatus() == BookingStatus.CONFIRMED)
                .mapToInt(Booking::getChildren)
                .sum();

        int totalPresent = (int) confirmedPassengers.stream().filter(p -> BooleanUtils.isTrue(p.getPresent())).count();
        int totalNonPresent = (int) confirmedPassengers.stream().filter(p -> BooleanUtils.isFalse(p.getPresent())).count();
        int notMarkedCount = confirmedPassengers.size() - totalPresent - totalNonPresent;

        TripStatisticsDto stats = TripStatisticsDto.builder()
                .tripResponseDto(new TripResponseDto(trip))
                .passengerList(confirmedPassengers)

                .bookingCount(totalBookings)
                .confirmedBookingCount(confirmedCount)
                .newBookingCount(newCount)
                .canceledBookingCount(canceledCount)

                .passengersCount(totalPassengers)
                .adultsCount(totalAdults)
                .childrenCount(totalChildren)

                .presentCount(totalPresent)
                .nonPresentCount(totalNonPresent)
                .nonMarkedPresentCount(notMarkedCount)
                .build();

        if (totalBookings != 0) {
            stats.setConfirmedBookingPercent(PriceAndDateUtil.roundDouble(100.0 * confirmedCount / totalBookings, 2));
            stats.setNewBookingPercent(PriceAndDateUtil.roundDouble(100.0 * newCount / totalBookings, 2));
            stats.setCanceledBookingPercent(PriceAndDateUtil.roundDouble(100.0 * canceledCount / totalBookings, 2));
        }

        if ((totalAdults + totalChildren) != 0) {
            stats.setAdultsPercent(PriceAndDateUtil.roundDouble(100.0 * totalAdults / (totalAdults + totalChildren), 2));
            stats.setChildrenPercent(PriceAndDateUtil.roundDouble(100.0 * totalChildren / (totalAdults + totalChildren), 2));
        }

        if (totalPassengers != 0) {
            stats.setPresentPercent(PriceAndDateUtil.roundDouble(100.0 * totalPresent / totalPassengers, 2));
            stats.setNonPresentPercent(PriceAndDateUtil.roundDouble(100.0 * totalNonPresent / totalPassengers, 2));
            stats.setNonMarkedPresentPercent(PriceAndDateUtil.roundDouble(100.0 * notMarkedCount / totalPassengers, 2));
        }

        return stats;
    }

    @Transactional
    @Override
    public void plusUsedSeats(Long tripId, Integer passengerCount) {
        tripRepository.plusUsedSeats(tripId, passengerCount);
    }

    @Override
    public DataTableResponse<TripV2> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(tripRepository, request);
    }

    @Transactional
    @Override
    public void create(TripV2 entity) {
        crudRepositoryHelper.create(tripRepository, entity);
    }

    @Transactional
    @Override
    public void update(TripV2 entity) {
        //validate check bookings
        crudRepositoryHelper.update(tripRepository, entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        //validate check bookings

        crudRepositoryHelper.delete(tripRepository, id);
    }

    @Override
    public TripV2 findById(Long id) {
        return tripRepository.findById(id).orElse(null);
    }
}
