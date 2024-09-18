package com.bookstore.simpleblog.service;

import com.bookstore.simpleblog.dto.CommentDto;
import com.bookstore.simpleblog.mapper.CommentMapper;
import com.bookstore.simpleblog.model.Comment;
import com.bookstore.simpleblog.repository.CommentRepository;
import com.bookstore.simpleblog.exceptions.CommentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public List<CommentDto> getAll() {
        return commentMapper.toDto(commentRepository.findAll());
    }

    public CommentDto getCommentById(Long id) {
        var commentDto = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
            return commentMapper.toDto(commentDto);
    }

    public CommentDto create(CommentDto commentdto) {
        var commentEntity = commentMapper.toEntity(commentdto);
         commentRepository.save(commentEntity);
         return commentdto;

    }

    public void update(Long id, CommentDto commentDto) {
        Comment updatedComment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
            updatedComment.setName(commentDto.getName());
            updatedComment.setEmail(commentDto.getEmail());
            updatedComment.setContext(commentDto.getContext());
            commentRepository.save(updatedComment);
    }

    public void delete(long id) {
        if(!commentRepository.existsById(id)) {
            throw new CommentNotFoundException(id);
        }
        commentRepository.deleteById(id);
    }
}
