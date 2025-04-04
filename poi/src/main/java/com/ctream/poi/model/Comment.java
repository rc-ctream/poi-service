package com.ctream.poi.model;


import java.time.Instant;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String message;
    private LocalDateTime timestamp;


    public Comment(String message, String author) {
        this.message = message;
        this.author = author;
    }

    @ManyToOne
    @JoinColumn(name = "poi_id", nullable = false)
    private POI poi;

    // Constructors
    public Comment() {
    }
    public Comment(String author, String message, LocalDateTime timestamp, POI poi) {
        this.author = author;
        this.message = message;
        this.timestamp = timestamp;
        this.poi = poi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public POI getPoi() {
        return poi;
    }

    public void setPoi(POI poi) {
        this.poi = poi;
    }


}
