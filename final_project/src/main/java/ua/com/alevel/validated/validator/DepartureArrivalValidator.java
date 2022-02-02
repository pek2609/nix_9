package ua.com.alevel.validated.validator;

import ua.com.alevel.validated.annotation.DepartureArrival;
import ua.com.alevel.web.dto.trip.TripRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class DepartureArrivalValidator implements ConstraintValidator<DepartureArrival, Object> {


    @Override
    public void initialize(DepartureArrival constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        TripRequestDto trip = (TripRequestDto) o;
        Date cur = new Date();
        return trip.getDeparture().compareTo(cur) * trip.getArrival().compareTo(trip.getDeparture()) > 0;
    }
}
