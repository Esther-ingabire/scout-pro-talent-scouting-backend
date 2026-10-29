package rw.ac.auca.scoutprotalentscouting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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

}
