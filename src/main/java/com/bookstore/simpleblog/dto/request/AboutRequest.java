package com.bookstore.simpleblog.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AboutRequest {
    private String title;
    private String content;
    private MultipartFile image;
}
