package com.bookstore.simpleblog.repository;

import com.bookstore.simpleblog.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {}
