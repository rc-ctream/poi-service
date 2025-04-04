package ctream.project.pois.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    @NotBlank(message = "Author cannot be blank")
    private String author;

    @NotBlank(message = "Message cannot be blank")
    private String message;
}
