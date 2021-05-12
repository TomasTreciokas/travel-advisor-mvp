package lt.traveladvisor.mvp.advisor.rest.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lt.traveladvisor.mvp.advisor.rest.request.validation.ValidTripDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@ValidTripDate
public class AdviseRequest {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final LocalDate endDate;

    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", message = "Wrong start city name format")
    private final String startCity;

    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", message = "Wrong end city name format")
    private final String endCity;

    @Min(value = 1, message = "Distance should be at least 1 kilometer")
    @Max(value = 140, message = "You can't walk more than 140 km a day according human capabilities, please insert lower preferredDistanceADay value")
    private final double preferredDistanceADay;

    @JsonCreator
    public AdviseRequest(LocalDate startDate, LocalDate endDate, double preferredDistanceADay, String startCity, String endCity) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.preferredDistanceADay = preferredDistanceADay;
        this.startCity = startCity;
        this.endCity = endCity;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getPreferredDistanceADay() {
        return preferredDistanceADay;
    }

    public String getStartCity() {
        return startCity;
    }

    public String getEndCity() {
        return endCity;
    }
}
