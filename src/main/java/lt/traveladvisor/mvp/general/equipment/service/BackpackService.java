package lt.traveladvisor.mvp.general.equipment.service;

import lt.traveladvisor.mvp.advisor.model.Backpack;
import lt.traveladvisor.mvp.advisor.model.Item;
import lt.traveladvisor.mvp.advisor.model.ItemType;
import lt.traveladvisor.mvp.advisor.rest.request.AdviseRequest;
import lt.traveladvisor.mvp.advisor.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class BackpackService {

    private static final long DEFAULT_GEAR_AMOUNT = 7;

    private final ItemService itemService;

    public BackpackService(ItemService itemService) {
        this.itemService = itemService;
    }

    public Backpack prepareBackpack(AdviseRequest adviseRequest) {
        Backpack backpack = new Backpack();

        long tripDuration = DAYS.between(adviseRequest.getStartDate(), adviseRequest.getEndDate());
        String weather = null;

        backpack.addAllClothes(prepareClothes(tripDuration, weather));
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

    private Set<Item> prepareClothes(long tripDuration, String weather) {
        return itemService.getRandomItemsByTypeWithLimit(tripDuration, ItemType.CLOTHES);
    }
}
