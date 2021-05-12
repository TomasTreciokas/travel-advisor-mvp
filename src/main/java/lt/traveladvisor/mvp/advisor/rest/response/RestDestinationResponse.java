package lt.traveladvisor.mvp.advisor.rest.response;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RestDestinationResponse {

    private final String weather;
    private final String restPlace;

    @JsonCreator
    public RestDestinationResponse(String weather, String restPlace) {
        this.weather = weather;
        this.restPlace = restPlace;
    }

    public String getWeather() {
        return weather;
    }

    public String getRestPlace() {
        return restPlace;
    }
}
