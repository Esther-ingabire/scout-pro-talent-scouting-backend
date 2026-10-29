package rw.ac.auca.scoutprotalentscouting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.scoutprotalentscouting.model.Athlete;
import rw.ac.auca.scoutprotalentscouting.service.AthleteService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/athletes")
public class AthleteController {
    @Autowired
    private AthleteService athleteServ;

    @PostMapping(value ="/save")
    public ResponseEntity<?> saveAthlete(@RequestBody Athlete athlete){
        String message = athleteServ.saveAthlete(athlete);
        if(message.equals("Athlete saved successfully")){
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllAthletes(){
        return new ResponseEntity<>(athleteServ.getAllAthletes(), HttpStatus.OK);
    }

    @GetMapping(value = "/by-position")
    public ResponseEntity<?>byPosition(@RequestParam String position){
        return new ResponseEntity<>(athleteServ.findByPosition(position), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?>getAthleteId(@PathVariable UUID id){
        Athlete athlete = athleteServ.getAthleteById(id);
        if(athlete == null){
            return new ResponseEntity<>("The athlete with that id does not exist", HttpStatus.NOT_FOUND);
        }

        return new  ResponseEntity<>(athlete, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateAthlete(@PathVariable UUID id, @RequestBody Athlete athlete){
        String message = athleteServ.updateAthlete(id, athlete);
        if(message.equals("Athlete updated successfully")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value ="/delete/{id}")

    public ResponseEntity<?>deleteAthlete(@PathVariable UUID id){
        String message = athleteServ.deleteAthlete(id);
        if(message.equals("Athlete deleted successfully")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }


}
