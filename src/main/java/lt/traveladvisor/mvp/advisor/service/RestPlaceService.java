package lt.traveladvisor.mvp.advisor.service;

import lt.traveladvisor.mvp.advisor.model.entities.RestDestination;
import lt.traveladvisor.mvp.advisor.rest.request.AdviseRequest;
import lt.traveladvisor.mvp.general.clients.positionstack.response.PlaceInfoResponse;
import lt.traveladvisor.mvp.general.cityposition.service.CityPositionService;
import lt.traveladvisor.mvp.advisor.model.PlaceCoordinates;
import lt.traveladvisor.mvp.general.utils.WeatherUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class RestPlaceService {

    public static final double EARTH_MEAN_RADIUS = 6378.137;

    private final CityPositionService cityPositionService;

    public RestPlaceService(CityPositionService cityPositionService) {
        this.cityPositionService = cityPositionService;
    }

    public Set<RestDestination> findRestPlaces(AdviseRequest request) {
        PlaceInfoResponse startCityCoordinates = cityPositionService.retrieveCityCoordinates(request.getStartCity());
        PlaceInfoResponse endCityCoordinates = cityPositionService.retrieveCityCoordinates(request.getEndCity());

        return getRestPlaces(request.getPreferredDistanceADay(), startCityCoordinates, endCityCoordinates);
    }

    public Set<RestDestination> determineWeatherForRestPlaces(Set<RestDestination> restDestinations) {
        return restDestinations.stream()
                .peek(rd -> rd.setWeather(WeatherUtils.forecast()))
                .collect(Collectors.toSet());
    }

    private Set<RestDestination> getRestPlaces(double preferredDistanceADay,
                                               PlaceInfoResponse startCityCoordinates,
                                               PlaceInfoResponse endCityCoordinates) {
        double tripDistance = calculateDistanceBetweenCities(startCityCoordinates, endCityCoordinates);

        Set<RestDestination> restPlaces = new HashSet<>();
        for (double restPlaceDistance = preferredDistanceADay; restPlaceDistance < tripDistance; restPlaceDistance += preferredDistanceADay) {
            if (tripDistance <= restPlaceDistance) {
                continue;
            }

            PlaceCoordinates restPlaceCoordinates =
                    getRestPlaceCoordinates(startCityCoordinates, endCityCoordinates, restPlaceDistance, tripDistance);

            String restPlaceName = cityPositionService.retrieveCityNameByCoordinates(restPlaceCoordinates);
            restPlaces.add(new RestDestination(restPlaceName));
        }

        return restPlaces;
    }

    private static PlaceCoordinates getRestPlaceCoordinates(PlaceInfoResponse startCityCoordinates,
                                                            PlaceInfoResponse endCityCoordinates,
                                                            double restPlaceDistance,
                                                            double tripDistance) {
        double startCityLatitude = startCityCoordinates.getLatitude();
        double startCityLongitude = startCityCoordinates.getLongitude();
        double endCityLatitude = endCityCoordinates.getLatitude();
        double endCityLongitude = endCityCoordinates.getLongitude();

        double ratio = restPlaceDistance / tripDistance;

        double restPlaceLatitude = (1 - ratio) * startCityLatitude + ratio * endCityLatitude;
        double restPlaceLongitude = (1 - ratio) * startCityLongitude + ratio * endCityLongitude;

        return new PlaceCoordinates(restPlaceLatitude, restPlaceLongitude);
    }

    private static Double calculateDistanceBetweenCities(PlaceInfoResponse startCityCoordinates, PlaceInfoResponse endCityCoordinates) {
        double startCityLat = startCityCoordinates.getLatitude();
        double startCityLong = startCityCoordinates.getLongitude();
        double endCityLat = endCityCoordinates.getLatitude();
        double endCityLong = endCityCoordinates.getLongitude();

        double distanceLat = rad(endCityLat - startCityLat);
        double distanceLong = rad(endCityLong - startCityLong);
        double a = Math.sin(distanceLat / 2) * Math.sin(distanceLat / 2) +
                Math.cos(rad(startCityLat)) * Math.cos(rad(endCityLat)) *
                        Math.sin(distanceLong / 2) * Math.sin(distanceLong / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_MEAN_RADIUS * c;
    }

    private static double rad(double x) {
        return x * Math.PI / 180;
    }
}
