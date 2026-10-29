package rw.ac.auca.scoutprotalentscouting.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.scoutprotalentscouting.model.Scout;
import rw.ac.auca.scoutprotalentscouting.service.ScoutService;

import java.util.UUID;

@RestController
@RequestMapping(value ="/api/scouts")
public class ScoutController {

    @Autowired
    private ScoutService scoutServ;

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveScout(@RequestBody Scout scout){
        String message = scoutServ.saveScout(scout);
        if(message.equals("Scout saved successfully")){
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        if(message.equals("The zone with that id does not exist")
                || message.equals("That sport does not exist") ){
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllScouts(){
        return new ResponseEntity<>(scoutServ.getAllScouts(), HttpStatus.OK);
    }

    @GetMapping(value = "/by-sport")
    public ResponseEntity<?>bySport(@RequestParam String name){
        return new ResponseEntity<>(scoutServ.findBySportName(name), HttpStatus.OK);
    }

    @PutMapping(value = "/{scoutId}/sports/{sportId}")
    public ResponseEntity<?> addSport(@PathVariable UUID scoutId, @PathVariable UUID sportId){
        String message = scoutServ.addSport(scoutId, sportId);
        if(message.equals("The scout with that id does not exist")
        || message.equals("That sport does not exist")){
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        if(message.equals("The scout already has that sport")){
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PatchMapping(value = "/{scoutId}/zone")
    public ResponseEntity<?>changeZone(@PathVariable UUID scoutId, @RequestParam int zoneNumber){
        String message = scoutServ.changeZone(scoutId, zoneNumber);
        if(message.equals("The scout with that id does not exist")
        || message.equals("The zone with that zoneNumber does not exist")){
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        if (message.equals("That zone is taken by another scout")){
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getScoutById(@PathVariable UUID id){
        Scout scout = scoutServ.getScoutById(id);
        if (scout == null){
            return new ResponseEntity<>("The scout with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(scout, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?>updateScout(@PathVariable UUID id, @RequestBody Scout scout){
        String message = scoutServ.updateScout(id, scout);
        if(message.equals("Scout updated successfully")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        if (message.equals("The zone with that id does not exist")
        || message.equals("That sport does not exist") ){
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{id}")

    public ResponseEntity<?> deleteScout(@PathVariable UUID id){
        String message = scoutServ.deleteScout(id);
        if (message.equals("Scout deleted successfully")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new  ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
