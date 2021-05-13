package lt.traveladvisor.mvp.advisor.rest.response.mapper;

import lt.traveladvisor.mvp.advisor.model.entities.Advise;
import lt.traveladvisor.mvp.advisor.rest.response.AdviseResponse;

public class AdviseResponseMapper {

    private AdviseResponseMapper() {
    }

    public static AdviseResponse from(Advise advise) {
        return new AdviseResponse(
                BackpackResponseMapper.from(advise.getBackpack()),
                RestDestinationMapper.from(advise.getRestDestinations())
        );
    }
}
