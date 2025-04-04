package com.salman.poi.service.mapper;

import com.salman.poi.service.dto.AddressDTO;
import com.salman.poi.service.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = LocationMapper.class)
public interface AddressMapper {
    AddressDTO toDto(Address address);
    Address toEntity(AddressDTO dto);
}