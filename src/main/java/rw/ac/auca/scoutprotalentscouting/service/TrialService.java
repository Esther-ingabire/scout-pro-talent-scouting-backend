package rw.ac.auca.scoutprotalentscouting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.scoutprotalentscouting.model.Athlete;
import rw.ac.auca.scoutprotalentscouting.model.Scout;
import rw.ac.auca.scoutprotalentscouting.model.Trial;
import rw.ac.auca.scoutprotalentscouting.model.TrialStatus;
import rw.ac.auca.scoutprotalentscouting.repository.AthleteRepository;
import rw.ac.auca.scoutprotalentscouting.repository.ScoutRepository;
import rw.ac.auca.scoutprotalentscouting.repository.TrialRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TrialService {

    @Autowired
    private TrialRepository  trialRepo;
    @Autowired
    private AthleteRepository athleteRepo;
    @Autowired
    private ScoutRepository scoutRepo;

    public String saveTrial(Trial trial){
        if(trial.getAthlete() == null || trial.getScout() == null){
            return "Athlete and scout are required";
        }
        Athlete athlete = athleteRepo.findById(trial.getAthlete().getId()).orElse(null);
        if(athlete == null){
            return "The athlete with that id does not exist";
        }
        Scout scout = scoutRepo.findById(trial.getScout().getId()).orElse(null);
        if(scout == null){
            return "The scout with that id does not exist";
        }
        trial.setAthlete(athlete);
        trial.setScout(scout);
        if(trial.getStatus() == null){
            trial.setStatus(TrialStatus.INVITED);
        }
        trialRepo.save(trial);
        return "Trial saved successfully";
    }
    public List<Trial> getAllTrials(){
        return trialRepo.findAll();
    }

    public Trial getTrialById(UUID id){
        return trialRepo.findById(id).orElse(null);
    }

    public String updateTrial(UUID id, Trial trial){
        Trial existing = trialRepo.findById(id).orElse(null);
        if(existing == null){
            return "The trial with that id does not exist";
        }

        existing.setTrialDate(trial.getTrialDate());
        existing.setVenue(trial.getVenue());
        existing.setStatus(trial.getStatus());
        if(trial.getAthlete() != null && trial.getAthlete().getId() != null){
            Athlete athlete = athleteRepo.findById(trial.getAthlete().getId()).orElse(null);
            if(athlete == null){
                return "The athlete with that id does not exist";
            }
            existing.setAthlete(athlete);
        }

        if(trial.getScout() != null && trial.getScout().getId() != null){
            Scout scout = scoutRepo.findById(trial.getScout().getId()).orElse(null);
            if(scout == null){
                return "The scout with that id does not exist";

            }
            existing.setScout(scout);
        }
        trialRepo.save(existing);
        return "Trial updated successfully";
    }
    public String deleteTrialById(UUID id){
        if(!trialRepo.existsById(id)){
            return "The trial with that id does not exist";
        }
        trialRepo.deleteById(id);
        return "Trial deleted successfully";
    }

    public List<Trial> findByStatus(TrialStatus status){
        return trialRepo.findByStatusOrderByTrialDateAsc(status);
    }

    public List<Trial> findBetween(LocalDate start, LocalDate end){
        return trialRepo.findByTrialDateBetweenOrderByTrialDateAsc(start, end);
    }
}
