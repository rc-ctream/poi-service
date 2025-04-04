package ctream.project.pois.controller;

import ctream.project.pois.exception.ResourceNotFoundException;
import ctream.project.pois.mapper.PoiMapper;
import ctream.project.pois.model.dto.CommentRequestDto;
import ctream.project.pois.model.dto.PointOfInterestDto;
import ctream.project.pois.service.PoiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "POI Management", description = "APIs for managing Points of Interest")
public class PoiController {
    private final PoiService poiService;
    private final PoiMapper poiMapper;

    @GetMapping("/pois")
    @Operation(summary = "Get all POIs", description = "Returns all POIs, optionally filtered by category")
    public ResponseEntity<List<PointOfInterestDto>> getAllPois(@RequestParam(required = false) String category) {
        List<PointOfInterestDto> pois;
        if (category != null && !category.isEmpty()) {
            pois = poiMapper.toDtoList(poiService.getPoisByCategory(category));
        } else {
            pois = poiMapper.toDtoList(poiService.getAllPois());
        }
        return ResponseEntity.ok(pois);
    }

    @GetMapping("/categories")
    @Operation(summary = "Get all categories", description = "Returns a list of all unique POI categories")
    public ResponseEntity<List<String>> getAllCategories() {
        return ResponseEntity.ok(poiService.getAllCategories());
    }

    @GetMapping("/pois/{id}")
    @Operation(summary = "Get POI by ID", description = "Returns details of a single POI")
    public ResponseEntity<PointOfInterestDto> getPoiById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(poiMapper.toDto(poiService.getPoiById(id)));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/pois/{id}/like")
    @Operation(summary = "Like POI", description = "Increments the like counter")
    public ResponseEntity<PointOfInterestDto> likePoi(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(poiMapper.toDto(poiService.likePoi(id)));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/pois/{id}/dislike")
    @Operation(summary = "Dislike POI", description = "Increments the dislike counter")
    public ResponseEntity<PointOfInterestDto> dislikePoi(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(poiMapper.toDto(poiService.dislikePoi(id)));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/pois/{id}/comment")
    @Operation(summary = "Add comment", description = "Adds a comment to the POI")
    public ResponseEntity<PointOfInterestDto> addComment(
            @PathVariable Integer id,
            @Valid @RequestBody CommentRequestDto comment) {
        try {
            return new ResponseEntity<>(
                    poiMapper.toDto(poiService.addComment(id, comment)),
                    HttpStatus.CREATED
            );
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }
}
