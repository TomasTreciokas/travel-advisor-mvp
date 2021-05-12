package lt.traveladvisor.mvp.advisor.service;

import lt.traveladvisor.mvp.advisor.model.Advise;
import lt.traveladvisor.mvp.advisor.model.Backpack;
import lt.traveladvisor.mvp.advisor.model.RestDestination;
import lt.traveladvisor.mvp.advisor.rest.request.AdviseRequest;
import lt.traveladvisor.mvp.general.equipment.service.BackpackService;
import lt.traveladvisor.mvp.general.distance.service.RestPlaceService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdvisorService {

    private final BackpackService backpackService;
    private final RestPlaceService tripRestPlaceService;
    private final AdviseService adviseService;

    public AdvisorService(BackpackService backpackService,
                          RestPlaceService walkDistanceService,
                          AdviseService adviseService) {
        this.backpackService = backpackService;
        this.tripRestPlaceService = walkDistanceService;
        this.adviseService = adviseService;
    }

    public Advise generateAdvise(AdviseRequest request) {
        Advise advise = new Advise();

        Set<RestDestination> restPlaces = tripRestPlaceService.findRestPlaces(request);
        Set<RestDestination> restPlacesWithWeather = tripRestPlaceService.determineWeatherForRestPlaces(restPlaces);
        Backpack backpack = backpackService.prepareBackpack(request);

        advise.addAllRestDestinations(restPlacesWithWeather);
        advise.setBackpack(backpack);

        return adviseService.save(advise);
    }
}
