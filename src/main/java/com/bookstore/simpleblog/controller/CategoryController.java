package com.bookstore.simpleblog.controller;

import com.bookstore.simpleblog.dto.CategoryDto;
import com.bookstore.simpleblog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public CategoryDto createCategory(CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable Long id, CategoryDto categoryDto) {
         categoryService.updateCategory(id,categoryDto);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteCategory(@PathVariable Long id) {
         categoryService.deleteCategory(id);
        return HttpStatus.NO_CONTENT;
    }

}
