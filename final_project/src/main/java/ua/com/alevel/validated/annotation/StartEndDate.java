package ua.com.alevel.validated.annotation;

import ua.com.alevel.validated.validator.StartEndDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({
        ElementType.TYPE,
        ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StartEndDateValidator.class)
@Documented
public @interface StartEndDate {

    String message() default "end of promotion must be after start";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
