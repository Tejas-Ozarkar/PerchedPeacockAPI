package com.perchedpeacock.ParkingLot.controller;

import com.perchedpeacock.ParkingLot.model.Booking;
import com.perchedpeacock.ParkingLot.model.HttpResponse;
import com.perchedpeacock.ParkingLot.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping()
    public ResponseEntity<List<Booking>> getBookings(){
        List<Booking> bookings = bookingService.getBookings();
        return ResponseEntity.status(200).body(bookings);x
    }

    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createBooking(@RequestBody Booking booking){
        bookingService.createBooking(booking);
        HttpResponse response = new HttpResponse();
        response.setMessage("Booking created");
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/checkout")
    public String checkout(@RequestBody Booking booking){
        bookingService.checkout(booking);
        return "Checkout";
    }

    @PostMapping("/payment")
    public String payment(@RequestBody Booking booking){
        bookingService.payment(booking);
        return "Checkout";
    }
}
