package com.bookstore.simpleblog.service;

import com.bookstore.simpleblog.dto.CategoryDto;
import com.bookstore.simpleblog.exceptions.CategoryNotFoundException;
import com.bookstore.simpleblog.exceptions.CommentNotFoundException;
import com.bookstore.simpleblog.mapper.CategoryMapper;
import com.bookstore.simpleblog.model.Category;
import com.bookstore.simpleblog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getAllCategories() {
        return categoryMapper.toDto(categoryRepository.findAll());
    }

    public CategoryDto getCategoryById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException(id)
        ));
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        var categoryEntity = categoryMapper.toEntity(categoryDto);
        categoryRepository.save(categoryEntity);
        return categoryDto;
    }

    public void updateCategory(Long id, CategoryDto categoryDto) {
        Category updatedCategory = categoryRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
        updatedCategory.setName(categoryDto.getName());
        updatedCategory.setSlug(categoryDto.getSlug());
        categoryRepository.save(updatedCategory);
    }

    public void deleteCategory(Long id) {
        if(!categoryRepository.existsById(id)) {
            throw new CommentNotFoundException(id);
        }
        categoryRepository.deleteById(id);
    }
}
