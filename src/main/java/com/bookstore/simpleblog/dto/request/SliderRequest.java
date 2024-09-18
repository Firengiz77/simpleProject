package com.bookstore.simpleblog.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SliderRequest {
    private String title;
    private String description;
    private String link;
    private String buttonText;

    private MultipartFile image;
}
