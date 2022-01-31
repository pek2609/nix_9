package ua.com.alevel.web.dto.bus;

import org.hibernate.validator.constraints.Range;
import ua.com.alevel.util.Messages;
import ua.com.alevel.web.dto.DtoRequest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class BusRequestDto extends DtoRequest {

    @NotBlank(message = Messages.NOT_NULL)
    @Pattern(regexp = "(https?:\\/\\/.*\\.(?:png|jpg))", message = Messages.INVALID_IMAGE_URL)
    private String imageUrl;

    @NotBlank(message = Messages.NOT_NULL)
    private String name;

    @Range(min = 8 , max = 100, message = "invalid number of seats(at least 8, max 100)")
    private Integer seats;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
