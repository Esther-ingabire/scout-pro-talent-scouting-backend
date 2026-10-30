package rw.ac.auca.scoutprotalentscouting.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rw.ac.auca.scoutprotalentscouting.model.SkillScore;
import rw.ac.auca.scoutprotalentscouting.model.Trial;

import java.util.List;
import java.util.UUID;

@Repository
public interface SkillScoreRepository extends JpaRepository<SkillScore, UUID> {
    @Query("SELECT d FROM SkillScore d WHERE d.trial.athlete.athleteCode = :athleteCode ORDER BY d.scoredDate DESC")
    List<SkillScore> findByAthleteCode(@Param("athleteCode") String athleteCode);

    boolean existsByTrial(Trial trial);


}
