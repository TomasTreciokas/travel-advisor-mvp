package lt.traveladvisor.mvp.advisor.rest.request.validation;

import lt.traveladvisor.mvp.advisor.rest.request.AdviseRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class TripDateValidator implements ConstraintValidator<ValidTripDate, AdviseRequest> {

    @Override
    public void initialize(ValidTripDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(AdviseRequest adviseRequest, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate startDate = adviseRequest.getStartDate();
        LocalDate endDate = adviseRequest.getEndDate();
        LocalDate currentDate = LocalDate.now();

        boolean isStartDateValid = startDate.isAfter(currentDate);
        boolean isEndDateValid = endDate.isAfter(currentDate);
        boolean isTripDurationValid = DAYS.between(startDate, endDate) > 0;

        return isStartDateValid && isEndDateValid && isTripDurationValid;
    }
}
