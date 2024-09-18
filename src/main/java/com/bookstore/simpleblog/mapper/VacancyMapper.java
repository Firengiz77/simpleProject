package com.bookstore.simpleblog.mapper;

import com.bookstore.simpleblog.dto.VacancyDto;
import com.bookstore.simpleblog.model.Vacancy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface VacancyMapper {
    VacancyDto toDto(Vacancy vacancy);
    Vacancy toEntity(VacancyDto vacancyDto);
    List<VacancyDto> toDto(List<Vacancy> vacancies);
    List<Vacancy> toEntity(List<VacancyDto> vacanciesDto);
}
