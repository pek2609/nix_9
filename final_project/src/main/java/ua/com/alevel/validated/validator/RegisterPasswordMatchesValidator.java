package ua.com.alevel.validated.validator;

import ua.com.alevel.validated.annotation.PasswordMatches;
import ua.com.alevel.web.dto.client.ClientRegisterRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegisterPasswordMatchesValidator implements ConstraintValidator<PasswordMatches, ClientRegisterRequestDto> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClientRegisterRequestDto user, ConstraintValidatorContext constraintValidatorContext) {
        ;
        return user.getPassword().equals(user.getPasswordConfirm());
    }
}
