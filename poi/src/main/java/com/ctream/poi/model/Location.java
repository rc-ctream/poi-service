package com.ctream.poi.model;


import jakarta.persistence.Embeddable;

@Embeddable
public class Location {
    private double latitude;
    private double longitude;

    // Constructors, getters, and setters
    public Location() {
    }
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
