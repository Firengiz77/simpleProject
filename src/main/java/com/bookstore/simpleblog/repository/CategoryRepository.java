package com.bookstore.simpleblog.repository;

import com.bookstore.simpleblog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
