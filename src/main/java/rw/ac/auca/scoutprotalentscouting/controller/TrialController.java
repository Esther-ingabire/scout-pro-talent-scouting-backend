package rw.ac.auca.scoutprotalentscouting.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.scoutprotalentscouting.model.Trial;
import rw.ac.auca.scoutprotalentscouting.model.TrialStatus;
import rw.ac.auca.scoutprotalentscouting.service.TrialService;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping(value= "/api/trials")
public class TrialController {

    @Autowired
    private TrialService trialServ;

    @PostMapping(value ="/save")
    public ResponseEntity<?> saveTrial(@RequestBody Trial trial){
        String message = trialServ.saveTrial(trial);
        if(message.equals("Trial saved successfully")){
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        if(message.equals("The athlete with that id does not exist")
        || message.equals("The scout with that id does not exist")){
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        if(message.equals("Trial date cannot be in the past")){
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        if(message.equals("Scout already runs a trial on that date")){
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllTrials(){
        return new ResponseEntity<>(trialServ.getAllTrials(), HttpStatus.OK);
    }

    @GetMapping(value = "/by-status")
    public ResponseEntity<?>byStatus(@RequestParam TrialStatus status){
        return new ResponseEntity<>(trialServ.findByStatus(status), HttpStatus.OK);
    }

    @GetMapping(value ="/between")
    public ResponseEntity<?>between(@RequestParam String start, @RequestParam String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        return new ResponseEntity<>(trialServ.findBetween(startDate, endDate), HttpStatus.OK);
    }

    @PatchMapping(value ="/{id}/status")
    public ResponseEntity<?>changeStatus(@PathVariable UUID id, @RequestParam TrialStatus status){
        String message = trialServ.changeStatus(id, status);
        if (message.equals("The trial with that id does not exist")){
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        if(message.startsWith("Cannot change status")){
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getTrialById(@PathVariable UUID id){
        Trial trial = trialServ.getTrialById(id);

        if(trial==null){
            return new ResponseEntity<>("The trial with that id does not exist",  HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trial, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?>updateTrial(@PathVariable UUID id, @RequestBody Trial trial){
        String message = trialServ.updateTrial(id, trial);
        if(message.equals("Trial updated successfully")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        if(message.equals("The athlete with that id does not exist")
        || message.equals("The scout with that id does not exist")){
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value= "/delete/{id}")
    public ResponseEntity<?> deleteTrial(@PathVariable UUID id){
        String message = trialServ.deleteTrialById(id);
        if(message.equals("Trial deleted successfully")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        if(message.equals("That trial has skill scores, delete them first")){
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        return new  ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
