package com.example.poiservice.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PoiRequest {
    private String name;
    private String category;
    private AddressDto address;
}
