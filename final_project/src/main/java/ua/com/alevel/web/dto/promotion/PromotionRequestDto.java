package ua.com.alevel.web.dto.promotion;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.alevel.util.Messages;
import ua.com.alevel.validated.annotation.StartEndDate;
import ua.com.alevel.web.dto.DtoRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@StartEndDate
public class PromotionRequestDto extends DtoRequest {

    @NotBlank(message = Messages.NOT_NULL)
    @Pattern(regexp = "(https?:\\/\\/.*\\.(?:png|jpg))", message = Messages.INVALID_IMAGE_URL)
    private String image;

    @NotBlank(message = Messages.NOT_NULL)
    private String name;

    @Range(min = 1, max = 99, message = Messages.INVALID_PERCENT)
    private Integer percent;

    @NotNull(message = Messages.NOT_NULL)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date start;

    @NotNull(message = Messages.NOT_NULL)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date end;

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
}
