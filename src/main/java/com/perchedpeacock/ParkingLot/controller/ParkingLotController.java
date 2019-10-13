package com.perchedpeacock.ParkingLot.controller;

import com.perchedpeacock.ParkingLot.model.ParkingLot;
import com.perchedpeacock.ParkingLot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking-lot")
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @GetMapping()
    public ResponseEntity<ParkingLot> getAvailableParkingLots(@RequestParam String id, @RequestParam int type){
        return ResponseEntity.ok().body(parkingLotService.getAvailableParkingLot(id, type));
    }
}
