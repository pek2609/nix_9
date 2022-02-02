package ua.com.alevel.validated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({
        ElementType.TYPE,
        ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ChildrenAdultsTripValidator.class, ChildrenAdultsOrderValidator.class})
@Documented
public @interface ChildrenAdults {

    String message() default "choose number of passengers";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
