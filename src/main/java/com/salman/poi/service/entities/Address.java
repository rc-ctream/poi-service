package com.salman.poi.service.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
@Embeddable
public class Address {
    private String street;
    private String city;
    private String postalCode;
    private String country;

    @Embedded
    private Location location;
}