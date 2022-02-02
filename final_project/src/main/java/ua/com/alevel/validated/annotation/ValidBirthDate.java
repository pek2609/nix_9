package ua.com.alevel.validated.annotation;

import ua.com.alevel.validated.validator.ValidBirthDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({
        ElementType.FIELD,
        ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidBirthDateValidator.class)
@Documented
public @interface ValidBirthDate {

    String message() default "birthdate might be from 1900 to 2010";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
