package com.example.poiservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String message;
    private Instant timestamp;

    @ManyToOne
    @JoinColumn(name = "poi_id")
    @ToString.Exclude
    private POI poi;
}
