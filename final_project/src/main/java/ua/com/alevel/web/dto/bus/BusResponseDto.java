package ua.com.alevel.web.dto.bus;

import ua.com.alevel.persistence.entity.Bus;
import ua.com.alevel.web.dto.DtoResponse;

public class BusResponseDto extends DtoResponse {

    private String imageUrl;
    private String name;
    private Integer seats;

    public BusResponseDto(Bus bus) {
        super(bus.getId(), bus.getCreated(), bus.getUpdated(), true);
        this.imageUrl = bus.getImageUrl();
        this.name = bus.getName();
        this.seats = bus.getSeats();
    }

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
