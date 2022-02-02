package ua.com.alevel.validated;

import ua.com.alevel.web.dto.trip.TripSearchRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChildrenAdultsTripValidator implements ConstraintValidator<ChildrenAdults, TripSearchRequest> {


    @Override
    public void initialize(ChildrenAdults constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TripSearchRequest trip, ConstraintValidatorContext constraintValidatorContext) {
        return !(trip.getAdults() == 0 && trip.getChildren() == 0) && !(trip.getAdults() < 0 || trip.getChildren() < 0);
    }
}
