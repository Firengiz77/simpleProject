package com.bookstore.simpleblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {
    private String name;
    private String description;
    private String profession;
    private String slug;
    private String image;
}
