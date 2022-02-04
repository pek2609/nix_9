package ua.com.alevel.web.dto.promotion;

import ua.com.alevel.persistence.entity.Promotion;
import ua.com.alevel.persistence.entity.Route;
import ua.com.alevel.web.dto.DtoResponse;

import java.util.Date;
import java.util.List;

public class PromotionResponseDto extends DtoResponse {

    private String image;
    private String name;
    private Integer percent;
    private Date start;
    private Date end;
    private Integer tripCount;
    private List<Route> routes;

    public PromotionResponseDto(Promotion promotion) {
        super(promotion.getId(), promotion.getCreated(), promotion.getUpdated(), promotion.isActive());
        this.image = promotion.getImage();
        this.name = promotion.getName();
        this.percent = promotion.getPercent();
        this.start = promotion.getStart();
        this.end = promotion.getEnd();
        this.tripCount = promotion.getTrips().size();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getTripCount() {
        return tripCount;
    }

    public void setTripCount(Integer tripCount) {
        this.tripCount = tripCount;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
