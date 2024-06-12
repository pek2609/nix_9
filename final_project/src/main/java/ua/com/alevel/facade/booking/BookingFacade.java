package ua.com.alevel.facade.booking;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.web.dto.BookingAdminResponseDto;
import ua.com.alevel.web.dto.datatable.PageData;

public interface BookingFacade {
    PageData<BookingAdminResponseDto> findAll(WebRequest request);

    BookingAdminResponseDto findById(Long id);

}
