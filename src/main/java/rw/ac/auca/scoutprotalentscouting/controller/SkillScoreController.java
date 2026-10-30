package rw.ac.auca.scoutprotalentscouting.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.scoutprotalentscouting.model.SkillScore;
import rw.ac.auca.scoutprotalentscouting.repository.SkillScoreRepository;
import rw.ac.auca.scoutprotalentscouting.service.SkillScoreService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/skill-scores")
public class SkillScoreController {
    @Autowired
    private SkillScoreService skillScoreServ;

    @PostMapping(value = "/save")
    public ResponseEntity<?> save (@RequestParam UUID trialId, @RequestBody SkillScore skillScore ){
        String message = skillScoreServ.saveSkillScore(trialId, skillScore);
        if (message.equals("Skill score saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }

        if(message.equals("The trial with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


    @GetMapping(value = "/athlete/{athleteCode}")
    public ResponseEntity<?> byAthlete(@PathVariable String athleteCode){
        return new ResponseEntity<>(skillScoreServ.findByAthleteCode(athleteCode), HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        String message = skillScoreServ.deleteSkillScore(id);
        if(message.equals("Skill score deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

}
