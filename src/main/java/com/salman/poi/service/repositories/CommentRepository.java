package com.salman.poi.service.repositories;

import com.salman.poi.service.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}