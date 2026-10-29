package rw.ac.auca.scoutprotalentscouting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rw.ac.auca.scoutprotalentscouting.model.Sport;

import java.util.List;
import java.util.UUID;


@Repository
public interface SportRepository extends JpaRepository<Sport, UUID> {
    @Query("SELECT s FROM Sport s WHERE s.scouts IS EMPTY")
    List<Sport> findUnused();
}
