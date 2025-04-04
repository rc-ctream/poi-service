package com.ctream.poi.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Embeddable
public class Address {
    private String street;
    private String city;

    @Column(name = "postal_code")
    private String postalCode;
    private String country;

    @Embedded
    private Location location;


    // Constructors
    public Address() {
    }
    public Address(String street, String city, String postalCode, String country, Location location) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.location = location;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
