package com.example.poiservice.controller;


import com.example.poiservice.dto.PoiResponse;
import com.example.poiservice.service.PoiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PoiController
{
    private final PoiService poiService;

    // GET /pois
    @Operation(summary = "Get all Points of Interest (POIs)", description = "Retrieve all POIs, optionally filtered by category")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of POIs returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid category parameter")
    })
    @GetMapping("/pois")
    public List<PoiResponse> getAllPois(@RequestParam(required = false) String category)
    {
        return poiService.getAllPois(category);
    }

    // GET /categories
    @Operation(summary = "Get all available categories", description = "Retrieve a list of all POI categories")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of categories returned successfully")
    })
    @GetMapping("/categories")
    public List<String> getAllCategories()
    {
        return poiService.getAllCategories();
    }

    // GET /pois/{id}
    @Operation(summary = "Get a specific POI by ID", description = "Retrieve details of a POI by its unique identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "POI details returned successfully"),
            @ApiResponse(responseCode = "404", description = "POI not found")
    })
    @GetMapping("/pois/{id}")
    public PoiResponse getPoi(@PathVariable UUID id)
    {
        return poiService.getPoiById(id);
    }

    // POST /pois/{id}/like
    @Operation(summary = "Like a POI", description = "Increment the like count for a specific POI")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "POI liked successfully"),
            @ApiResponse(responseCode = "404", description = "POI not found")
    })
    @PostMapping("/pois/{id}/like")
    public PoiResponse likePoi(@PathVariable UUID id)
    {
        return poiService.likePoi(id);
    }

    // POST /pois/{id}/dislike
    @PostMapping("/pois/{id}/dislike")
    @Operation(summary = "Dislike a POI", description = "Increment the dislike count for a specific POI")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "POI disliked successfully"),
            @ApiResponse(responseCode = "404", description = "POI not found")
    })
    public PoiResponse dislikePoi(@PathVariable UUID id)
    {
        return poiService.dislikePoi(id);
    }

    // POST /pois/{id}/comment (optional)
    @PostMapping("/pois/{id}/comment")
    @Operation(summary = "Add a comment to a POI", description = "Post a comment for a specific POI")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comment added successfully"),
            @ApiResponse(responseCode = "404", description = "POI not found")
    })
    public PoiResponse addComment(@Parameter(description = "ID of the POI to add a comment to", required = true)
                                      @PathVariable UUID id,
                                  @Parameter(description = "Author of the comment", required = true)
                                      @RequestParam String author,
                                  @Parameter(description = "Message content of the comment", required = true)
                                      @RequestParam String message)
    {
        return poiService.addComment(id, author, message);
    }
}
