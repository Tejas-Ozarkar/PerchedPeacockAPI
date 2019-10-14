package com.perchedpeacock.ParkingLot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document
public class ParkingSpace {

    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private float costPerHourTwoWheeler;

    @NotBlank
    private float costPerHourFourWheeler;

    @NotBlank
    private int twoWheelerParkingCount;

    @NotBlank
    private int fourWheelerParkingCount;

    private Address address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCostPerHourTwoWheeler() {
        return costPerHourTwoWheeler;
    }

    public void setCostPerHourTwoWheeler(float costPerHourTwoWheeler) {
        this.costPerHourTwoWheeler = costPerHourTwoWheeler;
    }

    public float getCostPerHourFourWheeler() {
        return costPerHourFourWheeler;
    }

    public void setCostPerHourFourWheeler(float costPerHourFourWheeler) {
        this.costPerHourFourWheeler = costPerHourFourWheeler;
    }

    public int getTwoWheelerParkingCount() {
        return twoWheelerParkingCount;
    }

    public void setTwoWheelerParkingCount(int twoWheelerParkingCount) {
        this.twoWheelerParkingCount = twoWheelerParkingCount;
    }

    public int getFourWheelerParkingCount() {
        return fourWheelerParkingCount;
    }

    public void setFourWheelerParkingCount(int fourWheelerParkingCount) {
        this.fourWheelerParkingCount = fourWheelerParkingCount;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
