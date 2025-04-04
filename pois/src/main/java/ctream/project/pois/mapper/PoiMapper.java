package ctream.project.pois.mapper;

import ctream.project.pois.model.dto.PointOfInterestDto;
import ctream.project.pois.model.entity.PointOfInterest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PoiMapper {
    PointOfInterestDto toDto(PointOfInterest poi);
    List<PointOfInterestDto> toDtoList(List<PointOfInterest> pois);

}
