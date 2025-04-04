package com.salman.poi.service.mapper;

import com.salman.poi.service.dto.CommentDTO;
import com.salman.poi.service.entities.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDTO toDto(Comment comment);
    Comment toEntity(CommentDTO dto);
}