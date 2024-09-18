package com.bookstore.simpleblog.mapper;

import com.bookstore.simpleblog.dto.SliderDto;
import com.bookstore.simpleblog.model.Image;
import com.bookstore.simpleblog.model.Slider;
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
public interface SliderMapper {
    @Mapping(source = "image", target = "image", qualifiedByName = "extractImageName")
    SliderDto toDto(Slider slider);

    @Mapping(source = "image", target = "image", ignore = true)
    Slider toEntity(SliderDto sliderDto);
    List<SliderDto> toDto(List<Slider> sliders);
    List<Slider> toEntity(List<SliderDto> sliderDtos);

    @Named("extractImageName")
    default String extractImageName(Image image) {
        return image != null ? image.getName() : null;
    }

}
