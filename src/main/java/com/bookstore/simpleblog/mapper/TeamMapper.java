package com.bookstore.simpleblog.mapper;

import com.bookstore.simpleblog.dto.TeamDto;
import com.bookstore.simpleblog.model.Image;
import com.bookstore.simpleblog.model.Team;
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
public interface TeamMapper {

    @Mapping(source = "image", target = "image", qualifiedByName = "extractImageName")
    TeamDto toDto(Team team);

    @Mapping(source = "image", target = "image", ignore = true)
    Team toEntity(TeamDto teamDto);

    List<TeamDto> toDto(List<Team> teams);

    List<Team> toEntity(List<TeamDto> teamDtos);

    @Named("extractImageName")
    default String extractImageName(Image image) {
        return image != null ? image.getName() : null;
    }

}
