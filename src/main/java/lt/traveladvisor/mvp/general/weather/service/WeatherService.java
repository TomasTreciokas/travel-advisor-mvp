package lt.traveladvisor.mvp.general.weather.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

import static java.util.Map.entry;

@Service
public class WeatherService {

    private static final Map<Integer, String> seasonsByMonth = Map.ofEntries(
            entry(1, "Winter"),
            entry(2, "Winter"),
            entry(3, "Spring"),
            entry(4, "Spring"),
            entry(5, "Summer"),
            entry(6, "Summer"),
            entry(7, "Summer"),
            entry(8, "Summer"),
            entry(9, "Fall"),
            entry(10, "Fall"),
            entry(11, "Fall"),
            entry(12, "Winter")
    );

    private final Random random;

    public WeatherService() {
        this.random = new Random();
    }

    public String forecast() {
        double probability = this.random.nextDouble();

        if (probability <= 0.1) {
            return "rain";
        } else if (probability <= 0.4) {
            return "snow";
        } else {
            return "sun";
        }
    }

    public String getSeason(LocalDateTime date) {
        return seasonsByMonth.get(date.getMonthValue());
    }
}
