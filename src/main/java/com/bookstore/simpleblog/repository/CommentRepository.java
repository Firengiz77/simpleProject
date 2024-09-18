package com.bookstore.simpleblog.repository;

import com.bookstore.simpleblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {}
