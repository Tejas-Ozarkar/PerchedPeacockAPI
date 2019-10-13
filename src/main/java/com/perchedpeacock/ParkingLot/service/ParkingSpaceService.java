package com.perchedpeacock.ParkingLot.service;

import com.perchedpeacock.ParkingLot.model.ParkingLot;
import com.perchedpeacock.ParkingLot.model.ParkingSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableAsync
public class ParkingSpaceService {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    ParkingLotService parkingLotService;

    public ParkingSpace createParkingSpace(ParkingSpace parkingSpace){
        ParkingSpace ps = mongoOperations.save(parkingSpace);
        parkingLotService.createParkingLots(ps.getId(),ps.getTwoWheelerParkingCount(),ps.getFourWheelerParkingCount());
        return parkingSpace;
    }

    public List<ParkingSpace> findByDistance(float longitude, float latitude, int distance) {
//        System.out.println("longitude"+longitude);
//        System.out.println("latitude"+latitude);
//        System.out.println("distance"+distance);
        Point basePoint =  new Point(longitude,latitude);
        Distance radius =  new Distance(distance, Metrics.MILES);
        Circle area = new Circle(basePoint, radius);
        Query query = new Query();
        query.addCriteria(Criteria.where("address.geoLocation").withinSphere(area));
        return mongoOperations.find(query,ParkingSpace.class);
    }
}
