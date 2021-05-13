package lt.traveladvisor.mvp.general.utils;

import lt.traveladvisor.mvp.advisor.model.enums.TemperateSeason;

import java.time.LocalDate;
import java.util.Map;
import java.util.Random;

import static java.util.Map.entry;

public class WeatherUtils {

    private static final Map<Integer, TemperateSeason> seasonsByMonth = Map.ofEntries(
            entry(1, TemperateSeason.WINTER),
            entry(2, TemperateSeason.WINTER),
            entry(3, TemperateSeason.SPRING),
            entry(4, TemperateSeason.SPRING),
            entry(5, TemperateSeason.SPRING),
            entry(6, TemperateSeason.SUMMER),
            entry(7, TemperateSeason.SUMMER),
            entry(8, TemperateSeason.SUMMER),
            entry(9, TemperateSeason.FALL),
            entry(10, TemperateSeason.FALL),
            entry(11, TemperateSeason.FALL),
            entry(12, TemperateSeason.WINTER)
    );

    private static final Random random = new Random();

    public static String forecast() {
        double probability = random.nextDouble();

        if (probability <= 0.1) {
            return "rain";
        } else if (probability <= 0.4) {
            return "snow";
        } else {
            return "sun";
        }
    }

    public static TemperateSeason getTemperateSeasonByDate(LocalDate date) {
        return seasonsByMonth.get(date.getMonthValue());
    }
}
