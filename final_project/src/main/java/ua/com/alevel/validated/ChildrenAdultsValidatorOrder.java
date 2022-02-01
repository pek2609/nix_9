package ua.com.alevel.validated;

import ua.com.alevel.web.dto.order.OrderRequestDto;
import ua.com.alevel.web.dto.trip.TripSearchRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChildrenAdultsValidatorOrder implements ConstraintValidator<ChildrenAdults, Object> {


    @Override
    public void initialize(ChildrenAdults constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        OrderRequestDto order = (OrderRequestDto) o;
        return !(order.getChildren() + order.getAdults() == 0);
    }
}
