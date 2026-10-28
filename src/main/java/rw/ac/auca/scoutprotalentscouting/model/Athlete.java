package rw.ac.auca.scoutprotalentscouting.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "athlete")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String athleteCode;
    private String position;
    private String phone;

    public Athlete() {
    }

    public Athlete(String firstName, String lastName, String athleteCode, String position, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.athleteCode = athleteCode;
        this.position = position;
        this.phone = phone;
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

    public String getAthleteCode() {
        return athleteCode;
    }

    public void setAthleteCode(String athleteCode) {
        this.athleteCode = athleteCode;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
