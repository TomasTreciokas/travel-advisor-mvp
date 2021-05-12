package lt.traveladvisor.mvp.advisor.service;

import lt.traveladvisor.mvp.advisor.model.Item;
import lt.traveladvisor.mvp.advisor.model.ItemType;
import lt.traveladvisor.mvp.advisor.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Set<Item> getRandomItemsByTypeWithLimit(long limit, ItemType itemType) {
        return itemRepository.getRandomItemsByTypeWithLimit(limit, itemType.toString());
    }
}
