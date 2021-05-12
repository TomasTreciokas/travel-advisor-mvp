package lt.traveladvisor.mvp.advisor.rest.request.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TripDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTripDate {

    String message() default "Inserted trip date interval is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};

}
