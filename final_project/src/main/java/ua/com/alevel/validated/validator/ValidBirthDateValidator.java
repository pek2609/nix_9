package ua.com.alevel.validated.validator;

import ua.com.alevel.validated.annotation.ValidBirthDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidBirthDateValidator implements ConstraintValidator<ValidBirthDate, Object> {


    @Override
    public void initialize(ValidBirthDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = (Date) o;
            Date min = sdf.parse("1900-01-01");
            Date max = sdf.parse("2010-01-01");
            return date.compareTo(min) * date.compareTo(max) < 0;
        } catch (Exception e) {
            return false;
        }
    }
}
