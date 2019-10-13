package com.perchedpeacock.ParkingLot.service;

import com.perchedpeacock.ParkingLot.model.Booking;
import com.perchedpeacock.ParkingLot.model.ParkingLot;
import com.perchedpeacock.ParkingLot.model.ParkingSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

@Component
public class BookingService {

    @Autowired
    MongoOperations mongoOperations;

    public void createBooking(Booking booking) {
        mongoOperations.save(booking);
    }

    public void checkout(Booking booking){
        try{
            Date checkin=new SimpleDateFormat("dd-M-yyyy hh:mm:ss").parse(booking.getCheckin());
            Date checkout=new SimpleDateFormat("dd-M-yyyy hh:mm:ss").parse(booking.getCheckout());
            long diff = checkout.getTime() - checkin.getTime();
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffMinutes = diff / (60 * 1000) % 60;
            ParkingLot parkingLot = mongoOperations.findById(booking.getLotId(),ParkingLot.class);
            ParkingSpace parkingSpace = mongoOperations.findById(parkingLot.getParkingSpaceId(),ParkingSpace.class);
            float amount = diffHours*parkingSpace.getCostPerHour() + (diffMinutes/parkingSpace.getCostPerHour());
            System.out.println("Amount"+ amount);
            booking.setAmount(amount);
        }catch(Exception e){
            System.out.println("Failed to date");
        }
        mongoOperations.save(booking);
        System.out.println("checkout");
    }

    public void payment(Booking booking){
        mongoOperations.save(booking);
    }
}
