package rw.ac.auca.scoutprotalentscouting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "sport")
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToMany(mappedBy = "sports")
    @JsonIgnore

    private List<Scout> scouts = new ArrayList<>();

    public Sport() {
    }

    public Sport(String name, List<Scout> scouts) {
        this.name = name;
        this.scouts = scouts;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Scout> getScouts() {
        return scouts;
    }

    public void setScouts(List<Scout> scouts) {
        this.scouts = scouts;
    }
}
