package com.perchedpeacock.ParkingLot.controller;

import com.perchedpeacock.ParkingLot.model.Vehicle;
import com.perchedpeacock.ParkingLot.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping()
    public List<Vehicle> getVehicles(){
        return vehicleService.getVehicles();
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody Vehicle vehicle){
        return vehicleService.save(vehicle);
    }
}
