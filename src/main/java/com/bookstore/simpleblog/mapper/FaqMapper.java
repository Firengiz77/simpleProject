package com.bookstore.simpleblog.mapper;

import com.bookstore.simpleblog.dto.FaqDto;
import com.bookstore.simpleblog.model.Faq;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)

public interface FaqMapper {
    FaqDto toDto(Faq faq);
    Faq toEntity(FaqDto faqDto);
    List<FaqDto> toDto(List<Faq> faq);
    List<Faq> toEntity(List<FaqDto> faqDtos);
}
