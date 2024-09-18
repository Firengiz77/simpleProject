package com.bookstore.simpleblog.repository;

import com.bookstore.simpleblog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
