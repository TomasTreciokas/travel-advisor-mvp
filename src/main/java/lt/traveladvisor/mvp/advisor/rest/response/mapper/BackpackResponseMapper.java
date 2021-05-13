package lt.traveladvisor.mvp.advisor.rest.response.mapper;

import lt.traveladvisor.mvp.advisor.model.entities.Backpack;
import lt.traveladvisor.mvp.advisor.model.entities.Item;
import lt.traveladvisor.mvp.advisor.rest.response.BackpackResponse;

import java.util.Set;
import java.util.stream.Collectors;

public class BackpackResponseMapper {

    private BackpackResponseMapper(){
    }

    public static BackpackResponse from(Backpack backpack){
        Set<Item> medicine = backpack.getMedicine();
        Set<Item> food = backpack.getFood();
        Set<Item> clothes = backpack.getClothes();
        Set<Item> gear = backpack.getGear();

        return new BackpackResponse(
                medicine.stream().map(Item::getName).collect(Collectors.toList()),
                food.stream().map(Item::getName).collect(Collectors.toList()),
                clothes.stream().map(Item::getName).collect(Collectors.toList()),
                gear.stream().map(Item::getName).collect(Collectors.toList())
        );
    }
}
