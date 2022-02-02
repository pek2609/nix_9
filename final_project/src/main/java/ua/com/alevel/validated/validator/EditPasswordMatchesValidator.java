package ua.com.alevel.validated.validator;

import ua.com.alevel.validated.annotation.PasswordMatches;
import ua.com.alevel.web.dto.client.ChangePasswordRequestDto;
import ua.com.alevel.web.dto.client.ClientRegisterRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EditPasswordMatchesValidator implements ConstraintValidator<PasswordMatches, ChangePasswordRequestDto> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ChangePasswordRequestDto user, ConstraintValidatorContext constraintValidatorContext) {;
        return user.getPassword().equals(user.getPasswordConfirm());
    }
}
