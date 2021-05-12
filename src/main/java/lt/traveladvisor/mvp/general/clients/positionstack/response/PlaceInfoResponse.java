package lt.traveladvisor.mvp.general.clients.positionstack.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaceInfoResponse {

    private final double latitude;
    private final double longitude;
    private String cityName;

    @JsonCreator
    public PlaceInfoResponse(double latitude, double longitude, @JsonProperty("name") String cityName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.cityName = cityName;
    }

    public PlaceInfoResponse(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCityName() {
        return cityName;
    }
}
