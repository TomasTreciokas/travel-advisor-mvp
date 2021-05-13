package lt.traveladvisor.mvp.advisor.model.entities;

import javax.persistence.*;

@Entity
public class RestDestination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String restPlace;
    private String weather;

    public RestDestination(){
    }

    public RestDestination(String restPlace){
        this.restPlace = restPlace;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getRestPlace() {
        return restPlace;
    }

    public void setRestPlace(String restPlace) {
        this.restPlace = restPlace;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
