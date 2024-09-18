package com.bookstore.simpleblog.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProjectRequest {
    private String name;
    private String description;
    private String slug;
    private MultipartFile image;
}
