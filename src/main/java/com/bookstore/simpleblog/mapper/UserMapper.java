package com.bookstore.simpleblog.mapper;


import com.bookstore.simpleblog.dto.RegisterDto;
import com.bookstore.simpleblog.dto.UserDto;
import com.bookstore.simpleblog.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UserMapper {
    User toEntityRegister(RegisterDto registerDto);
    RegisterDto toDtoRegister(User user);
    User toEntity(UserDto user);
    UserDto toDto(User user);
    List<UserDto> toDto(List<User> users);
    List<User> toEntity(List<UserDto> userDtos);
}
