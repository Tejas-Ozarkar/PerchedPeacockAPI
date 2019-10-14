package com.perchedpeacock.ParkingLot.service;

import com.perchedpeacock.ParkingLot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class BookingService {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    UserService userService;

    public List<Booking> getBookings(){
        User user = userService.getAuthorizeUser();
        Query query1 = new Query(Criteria.where("userId").is(user.getId()));
        List<Vehicle> vehicles = mongoOperations.find(query1, Vehicle.class);
        List<Booking> bookings = new ArrayList<Booking>();
        for(int i = 0;i<vehicles.size(); i++){
            Query query2 = new Query(Criteria.where("vehicleId").is(vehicles.get(i).getId()));
            bookings.addAll(mongoOperations.find(query2, Booking.class));
        }
        return bookings;
    }

    public void createBooking(Booking booking) {
        ParkingLot parkingLot = mongoOperations.findById(booking.getLotId(), ParkingLot.class);
        parkingLot.setStatus(false);
        mongoOperations.save(parkingLot);
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
        ParkingLot parkingLot = mongoOperations.findById(booking.getLotId(), ParkingLot.class);
        parkingLot.setStatus(true);
        mongoOperations.save(parkingLot);
        System.out.println("checkout");
    }

    public void payment(Booking booking){
        mongoOperations.save(booking);
    }
}
