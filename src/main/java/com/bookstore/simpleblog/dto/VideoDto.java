package com.bookstore.simpleblog.dto;

import com.bookstore.simpleblog.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoDto {
    private String name;
    private String link;
    private Long image;
}
