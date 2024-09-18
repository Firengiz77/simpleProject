package com.bookstore.simpleblog.mapper;

import com.bookstore.simpleblog.dto.ContactDto;
import com.bookstore.simpleblog.model.Contact;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)

public interface ContactMapper {
    ContactDto toDto(Contact contact);
    Contact toEntity(ContactDto contactDto);
    List<ContactDto> toDto(List<Contact> contact);
    List<Contact> toEntity(List<ContactDto> contactDto);
}
