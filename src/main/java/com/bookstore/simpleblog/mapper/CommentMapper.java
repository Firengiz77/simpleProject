package com.bookstore.simpleblog.mapper;

import com.bookstore.simpleblog.dto.CommentDto;
import com.bookstore.simpleblog.model.Comment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CommentMapper {
    CommentDto toDto(Comment comment);
    Comment toEntity(CommentDto commentDto);
    List<CommentDto> toDto(List<Comment> comments);
    List<Comment> toEntity(List<CommentDto> commentDtos);
}
