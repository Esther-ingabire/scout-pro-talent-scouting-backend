package rw.ac.auca.scoutprotalentscouting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rw.ac.auca.scoutprotalentscouting.model.Scout;
import rw.ac.auca.scoutprotalentscouting.model.Zone;


import java.util.List;
import java.util.UUID;


@Repository
public interface ScoutRepository extends JpaRepository<Scout, UUID> {
    @Query("SELECT s FROM Scout s JOIN s.sports sp WHERE LOWER(sp.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Scout> findBySportName(@Param("name") String name);

    boolean existsByZone(Zone zone);
}
