package com.example.poiservice.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class PoiResponse
{
    private UUID id;
    private String name;
    private String category;
    private int likes;
    private int dislikes;
    private AddressDto address;

    private List<CommentResponse> comments;
}
