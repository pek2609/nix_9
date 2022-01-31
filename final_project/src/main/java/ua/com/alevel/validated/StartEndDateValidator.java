package ua.com.alevel.validated;

import ua.com.alevel.web.dto.promotion.PromotionRequestDto;
import ua.com.alevel.web.dto.trip.TripRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class StartEndDateValidator implements ConstraintValidator<StartEndDate, Object> {


    @Override
    public void initialize(StartEndDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        PromotionRequestDto promotion = (PromotionRequestDto) o;
        return promotion.getStart().compareTo(promotion.getEnd()) < 0;
    }
}
