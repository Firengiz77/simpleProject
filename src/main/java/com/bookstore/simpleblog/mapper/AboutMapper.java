package com.bookstore.simpleblog.mapper;

import com.bookstore.simpleblog.dto.AboutDto;
import com.bookstore.simpleblog.model.About;
import com.bookstore.simpleblog.model.Image;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.Mapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface AboutMapper {

    @Mapping(source = "image", target = "image", qualifiedByName = "extractImageName")
    AboutDto toDto(About about);

    @Mapping(source = "image", target = "image", ignore = true)
    About toEntity(AboutDto aboutDto);
    List<About> toEntity(List<AboutDto> aboutDto);
    List<AboutDto> toDto(List<About> abouts);

    @Named("extractImageName")
    default String extractImageName(Image image) {
        return image != null ? image.getName() : null;
    }
}
