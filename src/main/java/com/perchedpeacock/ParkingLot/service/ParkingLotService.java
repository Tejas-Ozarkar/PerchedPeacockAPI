package com.perchedpeacock.ParkingLot.service;

import com.perchedpeacock.ParkingLot.model.ParkingLot;
import com.perchedpeacock.ParkingLot.model.ParkingSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParkingLotService {

    @Autowired
    MongoOperations mongoOperations;

    @Async
    public void createParkingLots(String id, int twoWheelerCount, int fourWheelerCount){
        for(int i =0 ;i<twoWheelerCount;i++){
            ParkingLot parkingLot = new ParkingLot();
            parkingLot.setParkingSpaceId(id);
            parkingLot.setType(1);
            parkingLot.setStatus(true);
            mongoOperations.save(parkingLot);
        }
        for(int i =0 ;i<fourWheelerCount;i++){
            ParkingLot parkingLot = new ParkingLot();
            parkingLot.setParkingSpaceId(id);
            parkingLot.setType(2);
            parkingLot.setStatus(true);
            mongoOperations.save(parkingLot);
        }
    }

    public ParkingLot getAvailableParkingLot(String id, int type){
        System.out.println(type);
        Query searchQuery = new Query(Criteria.where("parkingSpaceId").is(id).and("status").is(true).and("type").is(2));
        return mongoOperations.findOne(searchQuery, ParkingLot.class);
    }
}
