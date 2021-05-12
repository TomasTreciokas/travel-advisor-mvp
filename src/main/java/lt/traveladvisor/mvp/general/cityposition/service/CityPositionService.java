package lt.traveladvisor.mvp.general.cityposition.service;

import lt.traveladvisor.mvp.general.utils.JsonUtils;
import lt.traveladvisor.mvp.general.clients.positionstack.PositionstackClient;
import lt.traveladvisor.mvp.general.clients.positionstack.response.PlaceInfoResponse;
import lt.traveladvisor.mvp.general.clients.positionstack.response.PositionstackForwardResponse;
import org.springframework.stereotype.Service;

@Service
public class CityPositionService {

    private final PositionstackClient positionstackClient;

    public CityPositionService(PositionstackClient positionstackClient) {
        this.positionstackClient = positionstackClient;
    }

    /**
     * Retrieves city coordinates.
     * Positionstack api returns a list of occurrences with the city keyword we provided. There is a possibility, that more
     * than one occurrence is returned. Most of the times other returned elements consists same latitude/longitude
     * as the first element of the response list.
     **/
    public PlaceInfoResponse retrieveCityCoordinates(String city) {
        String coordinatesResponseBody = positionstackClient.getCoordinatesForCity(city);

        PositionstackForwardResponse response = JsonUtils.buildObjectFromJson(
                coordinatesResponseBody,
                PositionstackForwardResponse.class
        );

        return response.getData().get(0);
    }

    /**
     * Retrieves city name by provided longitude and latitude
     **/
    public String retrieveCityNameByCoordinates(PlaceInfoResponse restPlaceDistance) {

        String cityResponseBody = positionstackClient.getCityByCoordinates(restPlaceDistance.getLongitude(), restPlaceDistance.getLatitude());

        PositionstackForwardResponse response = JsonUtils.buildObjectFromJson(
                cityResponseBody,
                PositionstackForwardResponse.class
        );

        return response.getData().get(0).getCityName();
    }
}
