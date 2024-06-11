package ua.com.alevel.web.dto.route;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.util.Messages;
import ua.com.alevel.validated.annotation.DifferentTowns;
import ua.com.alevel.web.dto.DtoRequest;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@DifferentTowns
@AllArgsConstructor
@NoArgsConstructor
public class RouteRequestDto extends DtoRequest {

    @NotNull(message = Messages.NOT_NULL)
    private Long departureTownId;

    @NotNull(message = Messages.NOT_NULL)
    private Long arrivalTownId;

    @NotNull(message = Messages.NOT_NULL)
    private String description;

    private String imagePath;
}
