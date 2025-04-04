package com.salman.poi.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class POI {

    @Id
    private Long id;

    private String name;
    private String category;
    private int likes;
    private int dislikes;

    @Embedded
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "poi_id")
    private List<Comment> comments = new ArrayList<>();

}