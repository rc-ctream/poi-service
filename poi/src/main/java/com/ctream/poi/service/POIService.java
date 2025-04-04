package com.ctream.poi.service;

import com.fasterxml.jackson.core.type.TypeReference;

import com.ctream.poi.model.Comment;
import com.ctream.poi.model.POI;
import com.ctream.poi.repository.CommentRepository;
import com.ctream.poi.repository.POIRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class POIService {


    @Autowired
    private POIRepository poiRepository;

    @Autowired
    private CommentRepository commentRepository;


    public POIService(POIRepository poiRepository) {
        this.poiRepository = poiRepository;
    }


    @PostConstruct
    public void loadInitialData() throws IOException {
        // Read JSON file from resources folder
        ObjectMapper objectMapper = new ObjectMapper();
        Resource resource = new ClassPathResource("pois.json");
        List<POI> pois = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<POI>>() {});

        // Save POI objects into the database
        pois.forEach(poiRepository::save);
    }


    public POI getPOIById(Long id) {
        return poiRepository.findById(id).orElse(null);
    }

    public List<POI> getAllPOIs() {
        return poiRepository.findAll();
    }
    public List<String> getUniqueCategories() {
        return poiRepository.findDistinctCategories();
    }

    public POI likePOI(Long id) {
        POI poi = poiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("POI not found with id: " + id));
        poi.incrementLikes();
        return poiRepository.save(poi);
    }

    public POI addComment(Long poiId, String message, String author) {
        POI poi = poiRepository.findById(poiId)
                .orElseThrow(() -> new EntityNotFoundException("POI not found with id: " + poiId));

        Comment newComment = new Comment(message, author);
        poi.addComment(newComment);

        return poiRepository.save(poi);
    }

    public POI dislikePOI(Long id) {
        POI poi = poiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("POI not found with id: " + id));
        poi.incrementDislikes();
        return poiRepository.save(poi);
    }
}
