package com.perchedpeacock.ParkingLot.model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

public class Vehicle {
    @Id
    private String id;

    @NotBlank
    private String licence;

    @NotBlank
    private String model;

    @NotBlank
    private String color;

    @NotBlank
    private String userId;

    @NotBlank
    private String weight;

    // 1: 2 wheeler; 2: Four Wheeler
    @NotBlank
    private int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
