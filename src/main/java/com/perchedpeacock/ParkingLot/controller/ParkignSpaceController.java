package com.perchedpeacock.ParkingLot.controller;

import com.perchedpeacock.ParkingLot.model.ParkingSpace;
import com.perchedpeacock.ParkingLot.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/parking-space")
public class ParkignSpaceController {

    @Autowired
    ParkingSpaceService parkingSpaceService;

    @PostMapping()
    public ResponseEntity createParkingSpace(@RequestBody ParkingSpace parkingSpace){
        ParkingSpace space = parkingSpaceService.createParkingSpace(parkingSpace);
        return ResponseEntity.ok().body(space);
    }

    @GetMapping("/findByDistance")
    public ResponseEntity<List<ParkingSpace>> create(@RequestParam(value="lng") float longitude,
                                   @RequestParam(value="lat") float latitude,
                                   @RequestParam(value="d") int distance){

        List<ParkingSpace> parkingSpaces = parkingSpaceService.findByDistance(longitude,latitude, distance);
        return ResponseEntity.ok().body(parkingSpaces);
    }
}
