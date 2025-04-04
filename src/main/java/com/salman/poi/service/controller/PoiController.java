package com.salman.poi.service.controller;

import com.salman.poi.service.dto.PoiDTO;
import com.salman.poi.service.entities.Comment;
import com.salman.poi.service.service.PoiService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pois")
public class PoiController {

    private final PoiService poiService;

    public PoiController(PoiService poiService) {
        this.poiService = poiService;
    }

    @GetMapping
    public ResponseEntity<List<PoiDTO>> getAllPois(@RequestParam(required = false) String category) {
        if (category != null) {
            return ResponseEntity.ok(poiService.getPoisByCategory(category));
        }
        return ResponseEntity.ok(poiService.getAllPois());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoiDTO> getPoiById(@PathVariable Long id) {
        return ResponseEntity.ok(poiService.getPoiById(id));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        return ResponseEntity.ok(poiService.getAllCategories());
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<PoiDTO> likePoi(@PathVariable Long id) {
        PoiDTO updatedPoi = poiService.incrementLikes(id);
        return ResponseEntity.ok(updatedPoi);
    }

    @PostMapping("/{id}/dislike")
    public ResponseEntity<PoiDTO> dislikePoi(@PathVariable Long id) {
        PoiDTO updatedPoi = poiService.incrementDislikes(id);
        return ResponseEntity.ok(updatedPoi);
    }



    @PostMapping
    public ResponseEntity<PoiDTO> createPoi(@Valid @RequestBody PoiDTO poiDTO) {
        PoiDTO created = poiService.createPoi(poiDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PoiDTO> updatePoi(@PathVariable Long id, @Valid @RequestBody PoiDTO updatedDto) {
        return ResponseEntity.ok(poiService.updatePoi(id, updatedDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoi(@PathVariable Long id) {
        poiService.deletePoi(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<Void> addComment(@PathVariable Long id, @RequestBody Comment comment) {
        poiService.addComment(id, comment);
        return ResponseEntity.ok().build();
    }




}