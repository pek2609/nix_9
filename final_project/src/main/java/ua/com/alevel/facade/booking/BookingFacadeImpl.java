package ua.com.alevel.facade.booking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Booking;
import ua.com.alevel.persistence.entity.Passenger;
import ua.com.alevel.service.booking.BookingService;
import ua.com.alevel.util.FacadeUtil;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.web.dto.BookingAdminResponseDto;
import ua.com.alevel.web.dto.datatable.PageAndSizeData;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.datatable.SortData;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingFacadeImpl implements BookingFacade {

    private final BookingService bookingService;

    @Override
    public PageData<BookingAdminResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);

        DataTableResponse<Booking> all = bookingService.findAll(dataTableRequest);
        List<BookingAdminResponseDto> clients = mapItemsToDto(all);

        PageData<BookingAdminResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(clients, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public BookingAdminResponseDto findById(Long id) {
        BookingAdminResponseDto bookingAdminResponseDto = bookingService.findById(id)
                .map(booking -> {
                    BookingAdminResponseDto dto = new BookingAdminResponseDto(booking);
                    dto.setPassengersInfo(booking.getPassengers().stream().map(Passenger::getFullName).collect(Collectors.joining(", ")));
                    return dto;
                })
                .orElse(null);
        return bookingAdminResponseDto;
    }

    private List<BookingAdminResponseDto> mapItemsToDto(DataTableResponse<Booking> all) {
        return all.getItems()
                .stream()
                .map(BookingAdminResponseDto::new)
                .collect(Collectors.toList());
    }
}
