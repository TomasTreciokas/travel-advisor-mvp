package lt.traveladvisor.mvp.advisor.rest.response.mapper;

import lt.traveladvisor.mvp.advisor.model.entities.RestDestination;
import lt.traveladvisor.mvp.advisor.rest.response.RestDestinationResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RestDestinationMapper {

    private RestDestinationMapper(){}

    public static List<RestDestinationResponse> from(Set<RestDestination> restDestinations) {
        return restDestinations.stream()
                .map(rd -> new RestDestinationResponse(rd.getWeather(), rd.getRestPlace()))
                .collect(Collectors.toList());
    }
}
