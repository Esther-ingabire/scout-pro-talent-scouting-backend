package rw.ac.auca.scoutprotalentscouting.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.scoutprotalentscouting.model.Athlete;
import rw.ac.auca.scoutprotalentscouting.repository.AthleteRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepo;

    public String saveAthlete(Athlete athlete) {
        athleteRepo.save(athlete);
        return "Athlete saved successfully";
    }

    public List<Athlete> getAllAthletes() {
        return athleteRepo.findAll();
    }

    public Athlete getAthleteById(UUID id) {
        return athleteRepo.findById(id).orElse(null);
    }

    public String updateAthlete(UUID id, Athlete athlete) {
        Athlete existing = athleteRepo.findById(id).orElse(null);
        if (existing == null) {
            return "The athlete with that id does not exist";
        }
        existing.setFirstName(athlete.getFirstName());
        existing.setLastName(athlete.getLastName());
        existing.setAthleteCode(athlete.getAthleteCode());
        existing.setPosition(athlete.getPosition());
        existing.setPhone(athlete.getPhone());
        athleteRepo.save(existing);

        return "Athlete updated successfully";
    }

    public String deleteAthlete(UUID id) {
        if (!athleteRepo.existsById(id)) {
            return "The athlete with that id does not exist";
        }
        athleteRepo.deleteById(id);
        return "Athlete deleted successfully";

    }

    public List<Athlete> findByPosition(String position){
        return athleteRepo.findByPositionIgnoreCaseOrderByLastNameAsc(position);
    }
}
