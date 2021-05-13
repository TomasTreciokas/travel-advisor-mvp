package lt.traveladvisor.mvp.advisor.model.entities;

import lt.traveladvisor.mvp.advisor.model.enums.ItemType;

import javax.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemType type;

    private String name;

    public Item(){
    }

    public Item(ItemType itemType, String name){
        this.type = itemType;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
