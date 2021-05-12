package lt.traveladvisor.mvp.advisor.rest.response;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class BackpackResponse {

    private final List<String> medicine;
    private final List<String> food;
    private final List<String> clothes;
    private final List<String> gear;

    @JsonCreator
    public BackpackResponse(List<String> medicine,
                            List<String> food,
                            List<String> clothes,
                            List<String> gear) {
        this.medicine = medicine;
        this.food = food;
        this.clothes = clothes;
        this.gear = gear;
    }

    public List<String> getMedicine() {
        return medicine;
    }

    public List<String> getFood() {
        return food;
    }

    public List<String> getClothes() {
        return clothes;
    }

    public List<String> getGear() {
        return gear;
    }
}
