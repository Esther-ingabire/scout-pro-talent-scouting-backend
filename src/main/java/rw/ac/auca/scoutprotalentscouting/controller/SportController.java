package rw.ac.auca.scoutprotalentscouting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.scoutprotalentscouting.model.Sport;
import rw.ac.auca.scoutprotalentscouting.service.SportService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/sports")
public class SportController {
    @Autowired
    private SportService sportServ;


    @PostMapping(value = "/save")
    public ResponseEntity<?> saveSport(@RequestBody Sport sport){
        String message = sportServ.saveSport(sport);
        if(message.equals("Sport saved successfully")){
            return new ResponseEntity<>(message, HttpStatus.CREATED);

        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/all")

    public ResponseEntity<?> getAllSports(){
        return new ResponseEntity<>(sportServ.getAllSports(), HttpStatus.OK);
    }

    @GetMapping(value = "/unused")
    public ResponseEntity<?>unused(){
        return new ResponseEntity<>(sportServ.findUnused(), HttpStatus.OK);
    }

    @GetMapping("/{id}")

    public ResponseEntity<?> getSportById(@PathVariable UUID id){
        Sport sport = sportServ.getSportById(id);
        if(sport == null){
            return new ResponseEntity<>("The sport with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sport, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateSport(@PathVariable UUID id, @RequestBody Sport sport){
        String message = sportServ.updateSport(id, sport);
        if(message.equals("Sport updated successfully")){
            return new  ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{id}")

    public ResponseEntity<?> deleteSport(@PathVariable UUID id){
        String message = sportServ.deleteSport(id);
        if(message.equals("Sport deleted successfully")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
