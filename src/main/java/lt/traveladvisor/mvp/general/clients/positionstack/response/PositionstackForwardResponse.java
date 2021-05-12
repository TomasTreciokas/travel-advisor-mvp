package lt.traveladvisor.mvp.general.clients.positionstack.response;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class PositionstackForwardResponse {

    private final List<PlaceInfoResponse> data;

    @JsonCreator
    public PositionstackForwardResponse(List<PlaceInfoResponse> data) {
        this.data = data;
    }

    public List<PlaceInfoResponse> getData() {
        return data;
    }
}
