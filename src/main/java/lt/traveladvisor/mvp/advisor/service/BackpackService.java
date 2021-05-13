package lt.traveladvisor.mvp.advisor.service;

import lt.traveladvisor.mvp.advisor.model.entities.Backpack;
import lt.traveladvisor.mvp.advisor.model.entities.Item;
import lt.traveladvisor.mvp.advisor.model.enums.ItemType;
import lt.traveladvisor.mvp.advisor.rest.request.AdviseRequest;
import lt.traveladvisor.mvp.advisor.model.enums.TemperateSeason;
import lt.traveladvisor.mvp.general.utils.WeatherUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class BackpackService {

    private static final long DEFAULT_GEAR_AMOUNT = 7;
    private static final Set<TemperateSeason> COOL_SEASONS = Set.of(TemperateSeason.FALL, TemperateSeason.WINTER);

    private final ItemService itemService;

    public BackpackService(ItemService itemService) {
        this.itemService = itemService;
    }

    public Backpack prepareBackpack(AdviseRequest adviseRequest) {
        Backpack backpack = new Backpack();

        LocalDate startDate = adviseRequest.getStartDate();
        LocalDate endDate = adviseRequest.getEndDate();

        long tripDuration = DAYS.between(startDate, endDate);
        boolean isWarmClothesNeeded = isWarmClothesNeeded(startDate, endDate);

        backpack.addAllClothes(prepareClothes(tripDuration));
        backpack.addAllClothes(prepareWeatherSpecificClothes(isWarmClothesNeeded));
        backpack.addAllMedicine(prepareMedicine(tripDuration));
        backpack.addAllFood(prepareFood(tripDuration));
        backpack.addAllGear(prepareGear());

        return backpack;
    }

    private Set<Item> prepareGear() {
        return itemService.getRandomItemsByTypeWithLimit(DEFAULT_GEAR_AMOUNT, ItemType.GEAR);
    }

    private Set<Item> prepareMedicine(long tripDuration) {
        return itemService.getRandomItemsByTypeWithLimit(tripDuration, ItemType.MEDICINE);
    }

    private Set<Item> prepareFood(long tripDuration) {
        return itemService.getRandomItemsByTypeWithLimit(tripDuration, ItemType.FOOD);
    }

    private Set<Item> prepareClothes(long tripDuration) {
        return itemService.getRandomItemsByTypeWithLimit(tripDuration, ItemType.CLOTHES);
    }

    private Set<Item> prepareWeatherSpecificClothes(boolean isWarmClothesNeeded) {
        String searchCriteria = isWarmClothesNeeded ? "Warm" : "";

        return itemService.getClothesBySearchCriteria(searchCriteria);
    }

    public boolean isWarmClothesNeeded(LocalDate startDate, LocalDate endDate) {
        TemperateSeason startDateSeason = WeatherUtils.getTemperateSeasonByDate(startDate);
        TemperateSeason endDateSeason = WeatherUtils.getTemperateSeasonByDate(endDate);

        return COOL_SEASONS.contains(startDateSeason) || COOL_SEASONS.contains(endDateSeason);
    }
}
