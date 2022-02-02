package ua.com.alevel.validated.validator;

import ua.com.alevel.validated.annotation.ChildrenAdults;
import ua.com.alevel.web.dto.order.OrderRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChildrenAdultsOrderValidator implements ConstraintValidator<ChildrenAdults, OrderRequestDto> {


    @Override
    public void initialize(ChildrenAdults constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(OrderRequestDto order, ConstraintValidatorContext constraintValidatorContext) {
        return !(order.getAdults() == 0 && order.getChildren() == 0) && !(order.getAdults() < 0 || order.getChildren() < 0);
    }
}
