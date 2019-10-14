package com.perchedpeacock.ParkingLot.model;

public class AuthResponse {
    public int userType;
    public JwtResponse authToken;

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public JwtResponse getAuthToken() {
        return authToken;
    }

    public void setAuthToken(JwtResponse authToken) {
        this.authToken = authToken;
    }
}
