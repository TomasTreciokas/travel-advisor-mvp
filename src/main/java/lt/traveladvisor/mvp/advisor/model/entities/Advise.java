package lt.traveladvisor.mvp.advisor.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Advise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "backpack_id")
    private Backpack backpack;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "advise_id")
    private Set<RestDestination> restDestinations = new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public Set<RestDestination> getRestDestinations() {
        return restDestinations;
    }

    public void addAllRestDestinations(Set<RestDestination> restDestinations) {
        this.restDestinations.addAll(restDestinations);
    }
}
