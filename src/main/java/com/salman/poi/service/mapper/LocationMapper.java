package com.salman.poi.service.mapper;


import com.salman.poi.service.dto.LocationDTO;
import com.salman.poi.service.entities.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDTO toDto(Location location);
    Location toEntity(LocationDTO dto);
}