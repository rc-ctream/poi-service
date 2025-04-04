package com.example.poiservice.service;

import com.example.poiservice.dto.PoiResponse;
import com.example.poiservice.exception.PoiNotFoundException;
import com.example.poiservice.mapper.PoiMapper;
import com.example.poiservice.model.Comment;
import com.example.poiservice.model.POI;
import com.example.poiservice.repository.PoiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // Read-only by default
public class PoiService
{
    private final PoiRepository poiRepository;

    private final PoiMapper poiMapper;

    public List<PoiResponse> getAllPois(String category)
    {
        List<POI> pois = category == null ?
                poiRepository.findAll() :
                poiRepository.findByCategoryIgnoreCase(category);
        return pois.stream()
                .map(poiMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<String> getAllCategories() {
        return poiRepository.findDistinctCategories();
    }

    public PoiResponse getPoiById(UUID id)
    {
        return poiRepository.findById(id)
                .map(poiMapper::toResponse)
                .orElseThrow(() -> new PoiNotFoundException(id));
    }

    @Transactional
    public PoiResponse likePoi(UUID id) {
        POI poi = poiRepository.findById(id)
                .orElseThrow(() -> new PoiNotFoundException(id));
        poi.setLikes(poi.getLikes() + 1);
        return poiMapper.toResponse(poiRepository.save(poi));
    }

    @Transactional
    public PoiResponse dislikePoi(UUID id) {
        POI poi = poiRepository.findById(id)
                .orElseThrow(() -> new PoiNotFoundException(id));
        poi.setDislikes(poi.getDislikes() + 1);
        return poiMapper.toResponse(poiRepository.save(poi));
    }

    public PoiResponse addComment(UUID poiId, String author, String message) {
        POI poi = poiRepository.findById(poiId)
                .orElseThrow(() -> new PoiNotFoundException(poiId));

        poi.getComments().add(Comment.builder()
                .author(author)
                .message(message)
                .timestamp(Instant.now())
                .poi(poi)
                .build());

        return poiMapper.toResponse(poiRepository.save(poi));
    }


}
