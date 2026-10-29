package rw.ac.auca.scoutprotalentscouting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.auca.scoutprotalentscouting.model.Zone;

import java.util.UUID;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, UUID> {

}
