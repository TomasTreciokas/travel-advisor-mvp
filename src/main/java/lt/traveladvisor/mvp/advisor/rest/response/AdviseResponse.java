package lt.traveladvisor.mvp.advisor.rest.response;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class AdviseResponse {

    private final BackpackResponse backpack;
    private final List<RestDestinationResponse> restDestinations;

    @JsonCreator
    public AdviseResponse(BackpackResponse backpack,
                          List<RestDestinationResponse> restDestinations) {
        this.backpack = backpack;
        this.restDestinations = restDestinations;
    }

    public BackpackResponse getBackpackItems() {
        return backpack;
    }

    public List<RestDestinationResponse> getRestDestinations() {
        return restDestinations;
    }
}
