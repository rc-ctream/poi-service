package com.example.poiservice.dto;


import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class CommentResponse
{
    private String author;
    private String message;
    private Instant timestamp;
}
