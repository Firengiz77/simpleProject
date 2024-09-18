package com.bookstore.simpleblog.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class VideoRequest {
    private String name;
    private String link;
    private MultipartFile image;
}
