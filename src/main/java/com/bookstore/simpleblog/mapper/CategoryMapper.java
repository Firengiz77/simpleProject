package com.bookstore.simpleblog.mapper;


import com.bookstore.simpleblog.dto.CategoryDto;
import com.bookstore.simpleblog.model.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category toEntity(CategoryDto categoryDto);
    List<CategoryDto> toDto(List<Category> category);
    List<Category> toEntity(List<CategoryDto> categoryDto);
}
