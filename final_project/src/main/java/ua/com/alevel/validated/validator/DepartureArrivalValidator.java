package ua.com.alevel.validated.validator;

import ua.com.alevel.validated.annotation.DepartureArrival;
import ua.com.alevel.web.dto.trip.TripRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.util.Date;

public class DepartureArrivalValidator implements ConstraintValidator<DepartureArrival, Object> {


    @Override
    public void initialize(DepartureArrival constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        TripRequestDto trip = (TripRequestDto) o;
        LocalDateTime cur = LocalDateTime.now();
        return trip.getDeparture().compareTo(cur) * trip.getArrival().compareTo(trip.getDeparture()) > 0;
    }
}
