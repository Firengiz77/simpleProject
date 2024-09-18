package com.bookstore.simpleblog.mapper;

import com.bookstore.simpleblog.dto.TagDto;
import com.bookstore.simpleblog.model.Tag;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface TagMapper {
    TagDto toDto(Tag tag);
    Tag toEntity(TagDto tag);
    List<TagDto> toDto(List<Tag> tags);
    List<Tag> toEntity(List<TagDto> tagDtos);
}
