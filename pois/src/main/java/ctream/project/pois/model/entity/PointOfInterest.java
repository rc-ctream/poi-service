package ctream.project.pois.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pois")
public class PointOfInterest {

    @Id
    private Integer id;

    private String name;
    private String category;
    private int likes;
    private int dislikes;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "poi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setPoi(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setPoi(null);
    }
}
