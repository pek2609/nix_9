package ua.com.alevel.validated;

import ua.com.alevel.web.dto.trip.TripRequestDto;
import ua.com.alevel.web.dto.trip.TripSearchRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class ChildrenAdultsValidator implements ConstraintValidator<ChildrenAdults, Object> {


    @Override
    public void initialize(ChildrenAdults constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        TripSearchRequest trip = (TripSearchRequest) o;
        return !(trip.getChildren() + trip.getAdults() == 0);
    }
}
