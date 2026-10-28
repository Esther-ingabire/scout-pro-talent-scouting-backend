package rw.ac.auca.scoutprotalentscouting.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="trial")
public class Trial {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate trialDate;

    @Enumerated(EnumType.STRING)
    private TrialStatus status;
    private String venue;

    @ManyToOne
    @JoinColumn(name="athlete_id")
    private Athlete athlete;

    @ManyToOne
    @JoinColumn(name= "scout_id")
    private Scout scout;

    public Trial() {
    }

    public Trial(LocalDate trialDate, TrialStatus status, String venue, Athlete athlete, Scout scout) {
        this.trialDate = trialDate;
        this.status = status;
        this.venue = venue;
        this.athlete = athlete;
        this.scout = scout;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getTrialDate() {
        return trialDate;
    }

    public void setTrialDate(LocalDate trialDate) {
        this.trialDate = trialDate;
    }

    public TrialStatus getStatus() {
        return status;
    }

    public void setStatus(TrialStatus status) {
        this.status = status;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public Scout getScout() {
        return scout;
    }

    public void setScout(Scout scout) {
        this.scout = scout;
    }
}
