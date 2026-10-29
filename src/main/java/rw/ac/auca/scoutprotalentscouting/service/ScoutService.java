package rw.ac.auca.scoutprotalentscouting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.scoutprotalentscouting.model.Scout;
import rw.ac.auca.scoutprotalentscouting.model.Sport;
import rw.ac.auca.scoutprotalentscouting.model.Zone;
import rw.ac.auca.scoutprotalentscouting.repository.ScoutRepository;
import rw.ac.auca.scoutprotalentscouting.repository.SportRepository;
import rw.ac.auca.scoutprotalentscouting.repository.ZoneRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ScoutService {

    @Autowired
    private ScoutRepository scoutRepo;

    @Autowired
    private ZoneRepository zoneRepo;

    @Autowired
    private SportRepository sportRepo;

    public String saveScout(Scout scout) {
        if (scout.getZone() != null && scout.getZone().getId() != null) {
            Zone zone = zoneRepo.findById(scout.getZone().getId()).orElse(null);
            if (zone == null) {
                return "The zone with that id does not exist";
            }
            scout.setZone(zone);
        }
        if (scout.getSports() != null) {
            List<Sport> loaded = new ArrayList<>();
            for (Sport sport : scout.getSports()) {
                Sport found = sportRepo.findById(sport.getId()).orElse(null);
                if (found == null) {
                    return "That sport does not exist";
                }
                loaded.add(found);
            }
            scout.setSports(loaded);
        }
        scoutRepo.save(scout);
        return "Scout saved successfully";
    }
    public List<Scout>getAllScouts(){
        return scoutRepo.findAll();
    }

    public Scout getScoutById(UUID id){
        return scoutRepo.findById(id).orElse(null);
    }

    public String updateScout(UUID id, Scout scout) {
        Scout existing =scoutRepo.findById(id).orElse(null);
        if (existing == null) {
            return "The scout with that id does not exist";
        }
        existing.setFirstName(scout.getFirstName());
        existing.setLastName(scout.getLastName());
        existing.setEmail(scout.getEmail());
        if (scout.getZone() != null && scout.getZone().getId() != null) {
            Zone zone = zoneRepo.findById(scout.getZone().getId()).orElse(null);
            if(zone == null){
                return "The zone with that id does not exist";
            }
            existing.setZone(zone);
        }
        if (scout.getSports() != null) {
            List<Sport> loaded = new ArrayList<>();
            for (Sport sport : scout.getSports()) {
                Sport found = sportRepo.findById(sport.getId()).orElse(null);
                if(found == null){
                    return "That sport does not exist";
                }
                loaded.add(found);
            }
            existing.setSports(loaded);
        }
        scoutRepo.save(existing);
        return "Scout updated successfully";
    }

    public String deleteScout(UUID id) {
        if(!scoutRepo.existsById(id)){
            return "The scout with that id does not exist";
        }
        scoutRepo.deleteById(id);
        return "Scout deleted successfully";
    }

}
