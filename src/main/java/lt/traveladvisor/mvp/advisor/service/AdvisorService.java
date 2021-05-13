package lt.traveladvisor.mvp.advisor.service;

import lt.traveladvisor.mvp.advisor.model.entities.Advise;
import lt.traveladvisor.mvp.advisor.model.entities.Backpack;
import lt.traveladvisor.mvp.advisor.model.entities.RestDestination;
import lt.traveladvisor.mvp.advisor.rest.request.AdviseRequest;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdvisorService {

    private final BackpackService backpackService;
    private final RestPlaceService restPlaceService;
    private final AdviseService adviseService;

    public AdvisorService(BackpackService backpackService,
                          RestPlaceService walkDistanceService,
                          AdviseService adviseService) {
        this.backpackService = backpackService;
        this.restPlaceService = walkDistanceService;
        this.adviseService = adviseService;
    }

    public Advise generateAdvise(AdviseRequest request) {
        Advise advise = new Advise();

        Set<RestDestination> restPlaces = restPlaceService.findRestPlaces(request);
        Set<RestDestination> restPlacesWithWeather = restPlaceService.determineWeatherForRestPlaces(restPlaces);
        Backpack backpack = backpackService.prepareBackpack(request);

        advise.addAllRestDestinations(restPlacesWithWeather);
        advise.setBackpack(backpack);

        return adviseService.save(advise);
    }
}
