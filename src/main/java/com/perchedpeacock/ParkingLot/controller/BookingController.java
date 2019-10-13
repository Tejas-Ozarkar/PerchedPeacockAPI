package com.perchedpeacock.ParkingLot.controller;

import com.perchedpeacock.ParkingLot.model.Booking;
import com.perchedpeacock.ParkingLot.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/create")
    public String createBooking(@RequestBody Booking booking){
        bookingService.createBooking(booking);
        return "Booking created";
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
