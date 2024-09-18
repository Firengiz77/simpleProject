package com.bookstore.simpleblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDto {
    public String username;
    public String email;
    public String phone;
    public String address;
    public String file;
    public String password;
}
