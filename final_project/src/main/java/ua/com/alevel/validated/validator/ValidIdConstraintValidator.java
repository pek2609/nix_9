package ua.com.alevel.validated.validator;

import ua.com.alevel.validated.annotation.ValidId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidIdConstraintValidator implements ConstraintValidator<ValidId, Object> {

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            long id = Long.parseLong(String.valueOf(o));
            return id > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
