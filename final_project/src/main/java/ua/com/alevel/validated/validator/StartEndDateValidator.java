package ua.com.alevel.validated.validator;

import ua.com.alevel.validated.annotation.StartEndDate;
import ua.com.alevel.web.dto.promotion.PromotionRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
