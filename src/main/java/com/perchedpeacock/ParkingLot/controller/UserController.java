package com.perchedpeacock.ParkingLot.controller;

import com.perchedpeacock.ParkingLot.model.HttpResponse;
import com.perchedpeacock.ParkingLot.model.User;
import com.perchedpeacock.ParkingLot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> create(@RequestBody User user){
        return userService.register(user);
    }
}
