package com.ctream.poi.repository;

import com.ctream.poi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
