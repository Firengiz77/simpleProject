package com.bookstore.simpleblog.controller;

import com.bookstore.simpleblog.dto.TagDto;
import com.bookstore.simpleblog.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public List<TagDto> getAll() {
        return tagService.getAll();
    }

    @GetMapping("/{id}")
    public TagDto getTagById(@PathVariable Long id) {
        return tagService.get(id);
    }

    @PostMapping
    public ResponseEntity<TagDto> create(TagDto tag) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.create(tag));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,TagDto tag) {
        tagService.update(id, tag);
    }

    @DeleteMapping("/{id}")
    public HttpStatus destroy(@PathVariable Long id) {
        tagService.delete(id);
        return HttpStatus.NO_CONTENT;
    }
}
