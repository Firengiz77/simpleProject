package com.bookstore.simpleblog.controller;


import com.bookstore.simpleblog.dto.CommentDto;
import com.bookstore.simpleblog.service.CommentService;
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
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<CommentDto> getAll() {
        return commentService.getAll();
    }

    @GetMapping("/{id}")
    public CommentDto getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @PostMapping
    public CommentDto create(CommentDto comment) {
        return commentService.create(comment);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       CommentDto comment) {
        commentService.update(id, comment);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        commentService.delete(id);
        return HttpStatus.NO_CONTENT;
    }
}
