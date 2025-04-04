package com.salman.poi.service.mapper;

import com.salman.poi.service.dto.PoiDTO;
import com.salman.poi.service.entities.POI;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, CommentMapper.class})
public interface PoiMapper {
    PoiDTO toDto(POI record);
    POI toEntity(PoiDTO dto);

}