package lt.traveladvisor.mvp.general.clients.positionstack;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "services.positionstack")
public class PositionstackProperties {

    private final String cityUrl;
    private final String coordinatesUrl;
    private final String apiTokenKey;

    public PositionstackProperties(String cityUrl, String apiTokenKey, String coordinatesUrl) {
        this.cityUrl = cityUrl;
        this.apiTokenKey = apiTokenKey;
        this.coordinatesUrl = coordinatesUrl;
    }

    public String getCityUrl() {
        return cityUrl;
    }

    public String getApiTokenKey() {
        return apiTokenKey;
    }

    public String getCoordinatesUrl() {
        return coordinatesUrl;
    }
}