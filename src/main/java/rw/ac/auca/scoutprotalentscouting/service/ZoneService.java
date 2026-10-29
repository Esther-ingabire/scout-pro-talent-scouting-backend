package rw.ac.auca.scoutprotalentscouting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.scoutprotalentscouting.model.Zone;
import rw.ac.auca.scoutprotalentscouting.repository.ZoneRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepo;

    public String saveZone(Zone zone){
        zoneRepo.save(zone);
        return "Zone saved successfully";
    }

    public List<Zone> getAllZones(){
        return zoneRepo.findAll();
    }

    public Zone getZoneById(UUID id){
        return zoneRepo.findById(id).orElse(null);
    }

    public String updateZone(UUID id, Zone newData){
        Zone existing = zoneRepo.findById(id).orElse(null);
        if(existing == null){
            return "The zone with that id does not exist";

        }
        existing.setZoneNumber(newData.getZoneNumber());
        existing.setName(newData.getName());
        zoneRepo.save(existing);
        return "Zone updated successfully";
    }

    public String deleteZone(UUID id){
        if(!zoneRepo.existsById(id)){
            return "The zone with that id does not exist";
        }
        zoneRepo.deleteById(id);
        return "Zone deleted successfully";
    }

    public List<Zone> findFreeZones(){
        return zoneRepo.findFreeZones();
    }

}
