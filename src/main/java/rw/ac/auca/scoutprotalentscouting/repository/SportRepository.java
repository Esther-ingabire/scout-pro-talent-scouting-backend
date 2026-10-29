package rw.ac.auca.scoutprotalentscouting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.auca.scoutprotalentscouting.model.Sport;

import java.util.UUID;


@Repository
public interface SportRepository extends JpaRepository<Sport, UUID> {
}
