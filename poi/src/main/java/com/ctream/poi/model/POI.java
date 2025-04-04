package com.ctream.poi.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class POI {


    @Id
    private Long id;
    private String name;

    private String category;
    private int likes;

    private  int dislikes;

    @Embedded
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "com_id") // Foreign key
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    // Getters and Setters
    public void incrementLikes() {
        this.likes++;
    }

    public void incrementDislikes() {
        this.dislikes++;
    }
}

