package rw.ac.auca.scoutprotalentscouting.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.scoutprotalentscouting.model.Zone;
import rw.ac.auca.scoutprotalentscouting.service.ZoneService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/zones")
public class ZoneController {
    @Autowired
    private ZoneService zoneServ;

    @PostMapping(value = "/save")
    public ResponseEntity<?>saveZone(@RequestBody Zone zone){
        String message = zoneServ.saveZone(zone);
        if(message.equals("Zone saved successfully")){
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?>getAllZones(){
        return new ResponseEntity<>(zoneServ.getAllZones(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getZone(@PathVariable UUID id){
        Zone zone = zoneServ.getZoneById(id);
        if(zone == null){
            return new ResponseEntity<>("The zone with that id does not exist", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(zone, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?>updateZone(@PathVariable UUID id, @RequestBody Zone zone){
        String message = zoneServ.updateZone(id, zone);
        if(message.equals("Zone updated successfully")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value ="/delete/{id}")
    public ResponseEntity<?>deleteZone(@PathVariable UUID id){
        String message =zoneServ.deleteZone(id);
        if (message.equals("Zone deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }


}
