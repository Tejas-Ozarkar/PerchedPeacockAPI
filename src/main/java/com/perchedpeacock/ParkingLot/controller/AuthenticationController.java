package com.perchedpeacock.ParkingLot.controller;

import com.perchedpeacock.ParkingLot.config.JwtTokenUtil;
import com.perchedpeacock.ParkingLot.model.AuthResponse;
import com.perchedpeacock.ParkingLot.model.JwtRequest;
import com.perchedpeacock.ParkingLot.model.JwtResponse;
import com.perchedpeacock.ParkingLot.model.User;
import com.perchedpeacock.ParkingLot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;
    @Autowired
    MongoOperations mongoOperations;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<AuthResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getUsername());
        User user = mongoOperations.findOne(new Query(Criteria.where("username").is(userDetails.getUsername())), User.class);
        final String token = jwtTokenUtil.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserType(user.getType());
        authResponse.setAuthToken(new JwtResponse(token));
        return ResponseEntity.status(200).body(authResponse);
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}