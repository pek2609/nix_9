package ua.com.alevel.validated.annotation;

import ua.com.alevel.validated.validator.EditPasswordMatchesValidator;
import ua.com.alevel.validated.validator.RegisterPasswordMatchesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({
        ElementType.TYPE,
        ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RegisterPasswordMatchesValidator.class, EditPasswordMatchesValidator.class})
@Documented
public @interface PasswordMatches {

    String message() default "Passwords don't match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
