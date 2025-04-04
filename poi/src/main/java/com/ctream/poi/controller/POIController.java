package com.ctream.poi.controller;


import com.ctream.poi.model.POI;
import com.ctream.poi.service.POIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class POIController {


    @Autowired
    private POIService poiService;

    public POIController(POIService poiService) {
        this.poiService = poiService;
    }




    @GetMapping("/pois")
    public ResponseEntity<List<POI>> getAllPOIs() {
        return ResponseEntity.ok(poiService.getAllPOIs());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getUniqueCategories() {
        return ResponseEntity.ok(poiService.getUniqueCategories());
    }
    @GetMapping("/pois/{id}")
    public ResponseEntity<POI> getPOIById(@PathVariable Long id) {
        POI poi = poiService.getPOIById(id); // assuming your service returns POI or null
        return poi!= null? ResponseEntity.ok(poi):ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null);  // 200 OK
         }

    @PostMapping("/pois/{id}/like")
    public ResponseEntity<POI> likePOI(@PathVariable Long id) {
        POI updatedPOI = poiService.likePOI(id);
        return ResponseEntity.ok(updatedPOI);
    }

    @PostMapping("/pois/{id}/dislike")
    public ResponseEntity<POI> dislikePOI(@PathVariable Long id) {
        POI updatedPOI = poiService.dislikePOI(id);
        return ResponseEntity.ok(updatedPOI);
    }

    @PostMapping("/pois/{id}/comment")
    public ResponseEntity<POI> addComment(
            @PathVariable Long id,
            @RequestBody CommentRequest request) {

        POI updatedPOI = poiService.addComment(id, request.text(), request.author());
        return ResponseEntity.ok(updatedPOI);
    }

    public record CommentRequest(String text, String author) {}


}
