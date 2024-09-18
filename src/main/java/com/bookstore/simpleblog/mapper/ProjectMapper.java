package com.bookstore.simpleblog.mapper;

import com.bookstore.simpleblog.dto.ProjectDto;
import com.bookstore.simpleblog.model.Image;
import com.bookstore.simpleblog.model.Project;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)

public interface ProjectMapper {
    @Mapping(source = "image", target = "image", qualifiedByName = "extractImageName")
    ProjectDto toDto(Project project);

    @Mapping(source = "image", target = "image", ignore = true)
    Project toEntity(ProjectDto projectDto);
    List<ProjectDto> toDto(List<Project> projects);
    List<Project> toEntity(List<ProjectDto> projectDtos);

    @Named("extractImageName")
    default String extractImageName(Image image) {
        return image != null ? image.getName() : null;
    }
}
