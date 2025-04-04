package com.salman.poi.service.service;

import com.salman.poi.service.dto.PoiDTO;
import com.salman.poi.service.entities.Comment;
import com.salman.poi.service.entities.POI;
import com.salman.poi.service.exception.ResourceNotFoundException;
import com.salman.poi.service.mapper.PoiMapper;
import com.salman.poi.service.repositories.PoiRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PoiService {

    private final PoiRepository poiRepository;
    private final PoiMapper poiMapper;

    public PoiService(PoiRepository poiRepository, PoiMapper poiMapper) {
        this.poiRepository = poiRepository;
        this.poiMapper = poiMapper;
    }

    public List<PoiDTO> getAllPois() {
        return poiRepository.findAll()
                .stream()
                .map(poiMapper::toDto)
                .collect(Collectors.toList());
    }

    public PoiDTO getPoiById(Long id) {
        POI poi = poiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("POI not found with id: " + id));
        return poiMapper.toDto(poi);
    }

    public List<PoiDTO> getPoisByCategory(String category) {
        return poiRepository.findByCategoryIgnoreCase(category)
                .stream()
                .map(poiMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<String> getAllCategories() {
        return poiRepository.findAll()
                .stream()
                .map(POI::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }

    public PoiDTO incrementLikes(Long id) {
        POI poi = poiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("POI not found"));
        poi.setLikes(poi.getLikes() + 1);
        return poiMapper.toDto(poiRepository.save(poi));
    }

    public PoiDTO incrementDislikes(Long id) {
        POI poi = poiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("POI not found"));
        poi.setDislikes(poi.getDislikes() + 1);
        return poiMapper.toDto(poiRepository.save(poi));
    }

    public PoiDTO createPoi(PoiDTO dto) {
        POI poi = poiMapper.toEntity(dto);
        return poiMapper.toDto(poiRepository.save(poi));
    }

    public void deletePoi(Long id) {
        POI poi = poiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("POI not found with id: " + id));
        poiRepository.delete(poi);
    }

    public PoiDTO updatePoi(Long id, PoiDTO updatedDto) {
        POI existingPoi = poiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("POI not found with id: " + id));

        POI updatedPoi = poiMapper.toEntity(updatedDto);
        updatedPoi.setId(existingPoi.getId());
        return poiMapper.toDto(poiRepository.save(updatedPoi));
    }

    public void addComment(Long id, Comment comment) {
        POI poi = poiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("POI not found with id: " + id));
        poi.getComments().add(comment);
        poiRepository.save(poi);
    }


}