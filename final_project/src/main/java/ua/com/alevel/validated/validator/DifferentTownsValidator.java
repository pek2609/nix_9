package ua.com.alevel.validated.validator;

import ua.com.alevel.validated.annotation.DifferentTowns;
import ua.com.alevel.web.dto.route.RouteRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DifferentTownsValidator implements ConstraintValidator<DifferentTowns, Object> {


    @Override
    public void initialize(DifferentTowns constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        RouteRequestDto route = (RouteRequestDto) o;
        return !route.getDepartureTownId().equals(route.getArrivalTownId());
    }
}
