package ua.com.alevel.validated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({
        ElementType.TYPE,
        ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DepartureArrivalValidator.class)
@Documented
public @interface DepartureArrival {

    String message() default "the arrival must be after departure and shouldn't be in past";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
