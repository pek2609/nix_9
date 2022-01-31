package ua.com.alevel.validated;

import ua.com.alevel.web.dto.client.ClientRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        ClientRequestDto user = (ClientRequestDto) o;
        return user.getPassword().equals(user.getPasswordConfirm());
    }
}
