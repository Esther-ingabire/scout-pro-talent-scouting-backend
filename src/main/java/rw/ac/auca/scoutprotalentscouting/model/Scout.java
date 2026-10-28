package rw.ac.auca.scoutprotalentscouting.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "scout")
public class Scout {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @ManyToMany
    @JoinTable(
            name = "scout_sport",
            joinColumns = @JoinColumn(name = "scout_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id")
    )
    private List<Sport> sports = new ArrayList<>();

    public Scout() {
    }

    public Scout(String firstName, String lastName, String email, Zone zone, List<Sport> sports) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.zone = zone;
        this.sports = sports;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }
}
