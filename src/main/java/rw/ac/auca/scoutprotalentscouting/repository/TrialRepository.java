package rw.ac.auca.scoutprotalentscouting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rw.ac.auca.scoutprotalentscouting.model.Trial;
import rw.ac.auca.scoutprotalentscouting.model.TrialStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TrialRepository extends JpaRepository<Trial, UUID> {
    List<Trial> findByStatusOrderByTrialDateAsc(TrialStatus status);

    List<Trial> findByTrialDateBetweenOrderByTrialDateAsc(LocalDate start, LocalDate end);

    boolean existsByScoutIdAndTrialDateAndStatusNot(UUID scoutId, LocalDate trialDate, TrialStatus status);

    @Query("SELECT t.status, COUNT(t) FROM Trial t GROUP BY t.status")
    List<Object[]> countByStatus();


    @Modifying
    @Query("UPDATE Trial t SET t.status = :withdrawn WHERE t.scout.id = :scoutId AND t.trialDate = :date AND t.status <> :assessed")
    int withdrawByScoutAndDate(@Param("scoutId") UUID scoutId,
                               @Param("date") LocalDate date,
                               @Param("withdrawn") TrialStatus withdrawn,
                               @Param("assessed") TrialStatus assessed);

}
