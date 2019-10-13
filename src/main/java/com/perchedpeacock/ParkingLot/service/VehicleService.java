package com.perchedpeacock.ParkingLot.service;

import com.perchedpeacock.ParkingLot.model.User;
import com.perchedpeacock.ParkingLot.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleService {

    @Autowired
    private UserService userService;

    @Autowired
    private MongoOperations mongoOperations;

    public List<Vehicle> getVehicles(){
        User user = userService.getAuthorizeUser();
        Query searchQuery = new Query(Criteria.where("userId").is(user.getId()));
        return mongoOperations.find(searchQuery, Vehicle.class);
    }

    public ResponseEntity save(Vehicle vehicle){
//        Vehicle newVehicle = new Vehicle();
//        newVehicle.setUsername(user.getUsername());
//        newVehicle.setPassword(bcryptEncoder.encode(user.getPassword()));
        System.out.println(vehicle.getColor());
        User user = userService.getAuthorizeUser();
        vehicle.setUserId(user.getId());
        System.out.println(user.getId());
        mongoOperations.save(vehicle);
        return ResponseEntity.ok().body(vehicle);
    }
}
