package rw.ac.auca.scoutprotalentscouting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rw.ac.auca.scoutprotalentscouting.model.Zone;

import java.util.List;
import java.util.UUID;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, UUID> {

    @Query("SELECT z FROM Zone z WHERE NOT EXISTS(SELECT s FROM Scout s WHERE s.zone=z) ORDER BY z.zoneNumber")
    List<Zone> findFreeZones();
}
