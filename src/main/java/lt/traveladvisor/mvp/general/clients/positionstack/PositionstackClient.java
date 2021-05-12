package lt.traveladvisor.mvp.general.clients.positionstack;

import lt.traveladvisor.mvp.general.wrapper.HttpClientWrapper;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.Map;

@Component
public class PositionstackClient {

    private final HttpClientWrapper httpClient;
    private final PositionstackProperties positionstackProperties;

    public PositionstackClient(HttpClientWrapper httpclient,
                               PositionstackProperties positionstackProperties) {
        this.httpClient = httpclient;
        this.positionstackProperties = positionstackProperties;
    }

    public String getCoordinatesForCity(String city) {
        HttpResponse<String> response;
        String accessKey = positionstackProperties.getApiTokenKey();

        try {
            response = httpClient.executeGetRequest(
                    positionstackProperties.getCoordinatesUrl(),
                    Map.of("access_key", accessKey, "query", city)
            );

            return response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new WebApplicationException(
                    String.format("Error occurred during retrieval of coordinates for city: %s, " +
                            "please submit your request for trip advise once again...", city),
                    Response.status(500).build()
            );
        }
    }

    public String getCityByCoordinates(double longitude, double latitude) {
        HttpResponse<String> response;

        String queryParam = String.format("%s,%s", formatCoordinateNumber(latitude), formatCoordinateNumber(longitude));
        String accessKey = positionstackProperties.getApiTokenKey();

        try {
            response = httpClient.executeGetRequest(
                    positionstackProperties.getCityUrl(),
                    Map.of("access_key", accessKey, "query", queryParam)
            );

            return response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new WebApplicationException(
                    String.format(
                            "An error occurred during retrieval of city name for coordinates: %s,%s," +
                                    " please submit your request for trip advise once again...", latitude, longitude),
                    Response.status(500).build()
            );
        }
    }

    private static BigDecimal formatCoordinateNumber(double value) {
        return new BigDecimal(Double.toString(value)).setScale(5, RoundingMode.HALF_UP);
    }
}
