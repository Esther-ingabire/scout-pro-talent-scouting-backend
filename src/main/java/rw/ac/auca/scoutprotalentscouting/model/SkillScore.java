package rw.ac.auca.scoutprotalentscouting.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "skill_score")
public class SkillScore {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String skill;
    private String comment;
    private int score;
    private LocalDate scoredDate;

    @ManyToOne
    @JoinColumn(name= "trial_id")
    private Trial trial;

    public SkillScore() {
    }

    public SkillScore(String skill, String comment, int score, LocalDate scoredDate, Trial trial) {
        this.skill = skill;
        this.comment = comment;
        this.score = score;
        this.scoredDate = scoredDate;
        this.trial = trial;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDate getScoredDate() {
        return scoredDate;
    }

    public void setScoredDate(LocalDate scoredDate) {
        this.scoredDate = scoredDate;
    }

    public Trial getTrial() {
        return trial;
    }

    public void setTrial(Trial trial) {
        this.trial = trial;
    }
}
