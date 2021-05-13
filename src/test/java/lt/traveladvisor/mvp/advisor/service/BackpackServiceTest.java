package lt.traveladvisor.mvp.advisor.service;

import lt.traveladvisor.mvp.advisor.model.entities.Backpack;
import lt.traveladvisor.mvp.advisor.model.entities.Item;
import lt.traveladvisor.mvp.advisor.model.enums.ItemType;
import lt.traveladvisor.mvp.advisor.rest.request.AdviseRequest;
import lt.traveladvisor.mvp.general.utils.JsonUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BackpackServiceTest {

    private static final Path EXPECTED_BACKPACK_PATH = Path.of("src/test/resources/stubs/responses/expected-backpack.json");

    @InjectMocks
    private BackpackService backpackService;

    @Mock
    private ItemService itemService;

    @Test
    void prepareBackpack() throws IOException, JSONException {
        String expectedJson = Files.readString(EXPECTED_BACKPACK_PATH, StandardCharsets.UTF_8);
        AdviseRequest request = new AdviseRequest(
                LocalDate.of(2020, 10, 10),
                LocalDate.of(2020, 10, 15),
                20,
                "Vilnius",
                "Kaunas"
        );

        when(itemService.getRandomItemsByTypeWithLimit(7, ItemType.GEAR))
                .thenReturn(Set.of(new Item(ItemType.GEAR, "Mock gear")));
        when(itemService.getRandomItemsByTypeWithLimit(5, ItemType.MEDICINE))
                .thenReturn(Set.of(new Item(ItemType.MEDICINE, "Mock medicine")));
        when(itemService.getRandomItemsByTypeWithLimit(5, ItemType.FOOD))
                .thenReturn(Set.of(new Item(ItemType.FOOD, "Mock food")));
        when(itemService.getRandomItemsByTypeWithLimit(5, ItemType.CLOTHES))
                .thenReturn(Set.of(new Item(ItemType.CLOTHES, "Mock clothes")));
        when(itemService.getClothesBySearchCriteria("Warm"))
                .thenReturn(Set.of(new Item(ItemType.CLOTHES, "Warm mock clothes")));

        Backpack actualTripAdvise = backpackService.prepareBackpack(request);

        JSONAssert.assertEquals(expectedJson, JsonUtils.convertToString(actualTripAdvise), false);
    }
}