package lt.traveladvisor.mvp.advisor.model.entities;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Backpack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "backpack_item",
            joinColumns = {@JoinColumn(name = "backpack_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")}
    )
    @Where(clause = "type = 'MEDICINE'")
    private Set<Item> medicine = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "backpack_item",
            joinColumns = {@JoinColumn(name = "backpack_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")}
    )
    @Where(clause = "type = 'FOOD'")
    private Set<Item> food = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "backpack_item",
            joinColumns = {@JoinColumn(name = "backpack_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")}
    )
    @Where(clause = "type = 'CLOTHES'")
    private Set<Item> clothes = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "backpack_item",
            joinColumns = {@JoinColumn(name = "backpack_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")}
    )
    @Where(clause = "type = 'GEAR'")
    private Set<Item> gear = new HashSet<>();

    @OneToOne(
            mappedBy = "backpack",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Advise advise;

    public Backpack(){
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<Item> getMedicine() {
        return medicine;
    }

    public void addAllMedicine(Set<Item> medicine) {
        this.medicine.addAll(medicine);
    }

    public Set<Item> getFood() {
        return food;
    }

    public void addAllFood(Set<Item> food) {
        this.food.addAll(food);
    }

    public Set<Item> getClothes() {
        return clothes;
    }

    public void addAllClothes(Set<Item> clothes) {
        this.clothes.addAll(clothes);
    }

    public Advise getAdvise() {
        return advise;
    }

    public void setAdvise(Advise advise) {
        this.advise = advise;
    }

    public Set<Item> getGear() {
        return gear;
    }

    public void addAllGear(Set<Item> gear) {
        this.gear.addAll(gear);
    }

    public void addClothes(Item item){
        this.clothes.add(item);
    }
}
