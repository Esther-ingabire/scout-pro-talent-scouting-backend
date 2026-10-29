package rw.ac.auca.scoutprotalentscouting.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.scoutprotalentscouting.model.Sport;
import rw.ac.auca.scoutprotalentscouting.repository.SportRepository;

import java.util.List;
import java.util.UUID;

@Service
public class SportService {

    @Autowired
    private SportRepository sportRepo;

    public String saveSport(Sport sport){
        sportRepo.save(sport);
        return "Sport saved successfully";
    }

    public List<Sport> getAllSports(){
        return sportRepo.findAll();
    }

    public Sport getSportById(UUID id){
        return sportRepo.findById(id).orElse(null);
    }
    public String updateSport(UUID id, Sport newData){
        Sport existing = sportRepo.findById(id).orElse(null);
        if(existing == null){
            return "The sport with that id does not exist";
        }
        existing.setName(newData.getName());
        sportRepo.save(existing);
        return "Sport updated successfully";
    }

    public String deleteSport(UUID id){
        if (!sportRepo.existsById(id)){
            return "The sport with that id does not exist";
        }
        sportRepo.deleteById(id);
        return "Sport deleted successfully";
    }


}
