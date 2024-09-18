package com.bookstore.simpleblog.controller;

import com.bookstore.simpleblog.model.Image;
import com.bookstore.simpleblog.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public Image create(@RequestParam("file") MultipartFile file) throws IOException {
        return imageService.create(file);
    }
    public Image update(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file) throws IOException {
       return imageService.update(id,file);
    }

//    @PutMapping("/{id}")
//    public String update(@PathVariable("id") Long id,
//                         @RequestParam("file") MultipartFile file) {
//        return imageService.update(id, file);
//
//    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        return imageService.delete(id);
//    }
}
