package com.bookstore.simpleblog.mapper;


import com.bookstore.simpleblog.dto.BlogDto;
import com.bookstore.simpleblog.model.Blog;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface BlogMapper {
    BlogDto toDto(Blog blog);
    Blog toEntity(BlogDto blogDto);
    List<BlogDto> toDto(List<Blog> blogs);
    List<Blog> toEntity(List<BlogDto> blogDto);
}
