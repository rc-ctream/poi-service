package ctream.project.pois.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointOfInterestDto {
    private Integer id;
    private String name;
    private String category;
    private int likes;
    private int dislikes;
    private AddressDto address;
    private List<CommentDto> comments;
}
