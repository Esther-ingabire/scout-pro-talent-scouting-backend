package rw.ac.auca.scoutprotalentscouting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rw.ac.auca.scoutprotalentscouting.model.Athlete;


import java.util.List;
import java.util.UUID;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, UUID> {

    List<Athlete> findByPositionIgnoreCaseOrderByLastNameAsc(String position);

    @Query("SELECT DISTINCT t.athlete FROM Trial t WHERE t.scout.id =:scoutId")
    List<Athlete> findOfScout(@Param("scoutId") UUID scoutId);
}
