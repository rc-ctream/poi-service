package com.salman.poi.service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PoiDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @Min(value = 0, message = "Likes must be 0 or more")
    private int likes;

    @Min(value = 0, message = "Dislikes must be 0 or more")
    private int dislikes;

    @NotNull(message = "Address is required")
    @Valid
    private AddressDTO address;

    private List<CommentDTO> comments;

}
