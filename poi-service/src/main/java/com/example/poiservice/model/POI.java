package com.example.poiservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class POI
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @Builder.Default
    private int likes = 0;

    @Builder.Default
    private int dislikes = 0;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "poi", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();
}

