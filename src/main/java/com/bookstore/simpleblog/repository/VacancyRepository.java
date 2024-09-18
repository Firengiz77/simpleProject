package com.bookstore.simpleblog.repository;

import com.bookstore.simpleblog.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {}
