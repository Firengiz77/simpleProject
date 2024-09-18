package com.bookstore.simpleblog.repository;

import com.bookstore.simpleblog.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {}
