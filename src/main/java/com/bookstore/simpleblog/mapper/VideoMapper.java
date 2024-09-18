package com.bookstore.simpleblog.mapper;


import com.bookstore.simpleblog.dto.VideoDto;
import com.bookstore.simpleblog.model.Image;
import com.bookstore.simpleblog.model.Video;
import org.mapstruct.Mapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface VideoMapper {

    @Mapping(source = "image", target = "image", qualifiedByName = "extractImageName")
    VideoDto toDto(Video video);

    @Mapping(source = "image", target = "image", ignore = true)
    Video toEntity(VideoDto videoDto);

    List<VideoDto> toDto(List<Video> videoList);

    List<Video> toEntity(List<VideoDto> videoDtoList);

    @Named("extractImageName")
    default Long extractImageName(Image image) {
        return image != null ? image.getId() : null;
    }
}
