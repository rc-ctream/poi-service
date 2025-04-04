package com.example.poiservice.exception;

import java.util.UUID;

public class PoiNotFoundException extends RuntimeException
{
    private final UUID id;

    public PoiNotFoundException(UUID id) {
        super("POI not found with id: " + id);
        this.id = id;
    }
}
