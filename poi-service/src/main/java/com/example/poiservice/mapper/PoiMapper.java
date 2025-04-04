package com.example.poiservice.mapper;

import com.example.poiservice.dto.PoiRequest;
import com.example.poiservice.dto.PoiResponse;
import com.example.poiservice.model.POI;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PoiMapper {
    PoiResponse toResponse(POI poi);

}