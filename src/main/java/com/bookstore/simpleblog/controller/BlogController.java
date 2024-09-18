package com.bookstore.simpleblog.controller;

import com.bookstore.simpleblog.dto.BlogDto;
import com.bookstore.simpleblog.dto.response.BlogResponse;
import com.bookstore.simpleblog.model.Blog;
import com.bookstore.simpleblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public List<BlogDto> getAll() {
        return blogService.getAll();
    }

    @GetMapping("/{id}")
    public BlogDto getBlogById(@PathVariable Long id) {
        return blogService.getBlogById(id);
    }

    @PostMapping
    public BlogDto create(BlogDto blog) {
        return blogService.create(blog);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,BlogDto blog) {
        blogService.update(id, blog);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        blogService.delete(id);
        return HttpStatus.NO_CONTENT;
    }

    @GetMapping("/getdesc/{description}")
    public List<BlogResponse> getBlogByDescription(@PathVariable String description) {
        return  blogService.getBlogByDescription(description);
    }


}
