package rw.ac.auca.scoutprotalentscouting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.scoutprotalentscouting.model.SkillScore;
import rw.ac.auca.scoutprotalentscouting.model.Trial;
import rw.ac.auca.scoutprotalentscouting.model.TrialStatus;
import rw.ac.auca.scoutprotalentscouting.repository.SkillScoreRepository;
import rw.ac.auca.scoutprotalentscouting.repository.TrialRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class SkillScoreService {

    @Autowired
    private SkillScoreRepository skillScoreRepo;

    @Autowired
    private TrialRepository trialRepo;


    public String saveSkillScore(UUID trialId, SkillScore skillScore){
        Trial trial = trialRepo.findById(trialId).orElse(null);
        if(trial==null){
            return "The trial with that id does not exist";
        }

        if (trial.getStatus() != TrialStatus.ASSESSED){
            return "Skill scores are only ASSESSED trial";
        }
        skillScore.setTrial(trial);
        skillScore.setScoredDate(LocalDate.now());
        skillScoreRepo.save(skillScore);

        return "Skill score saved successfully";
    }

    public List<SkillScore> findByAthleteCode(String athleteCode){
        return skillScoreRepo.findByAthleteCode(athleteCode);
    }

    public String deleteSkillScore(UUID id){
        if (!skillScoreRepo.existsById(id)){
            return "The skill score with that id does not exist";
        }
        skillScoreRepo.deleteById(id);
        return "Skill score deleted successfully";
    }
}
