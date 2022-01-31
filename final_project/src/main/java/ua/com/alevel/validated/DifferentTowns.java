package ua.com.alevel.validated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({
        ElementType.TYPE,
        ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DifferentTownsValidator.class)
@Documented
public @interface DifferentTowns {

    String message() default "the departure and arrival town is the same";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
