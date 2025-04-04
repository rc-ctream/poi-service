package com.salman.poi.service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Postal Code is required")
    private String postalCode;

    @NotBlank(message = "Country is required")
    private String country;

    @NotNull(message = "Location must not be null")
    @Valid
    private LocationDTO location;
}