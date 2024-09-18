package com.bookstore.simpleblog.service;


import com.bookstore.simpleblog.dto.TagDto;
import com.bookstore.simpleblog.mapper.TagMapper;
import com.bookstore.simpleblog.model.Tag;
import com.bookstore.simpleblog.repository.TagRepository;
import com.bookstore.simpleblog.exceptions.TagNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public List<TagDto> getAll() {
        return tagMapper.toDto(tagRepository.findAll());
    }

    public TagDto get(Long id) {
        var tag = tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException(id));
        return tagMapper.toDto(tag);
    }

    public void delete(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new TagNotFoundException(id);
        }
        tagRepository.deleteById(id);
    }

    public TagDto create(TagDto tagDto) {
        var tagEntity = tagMapper.toEntity(tagDto);
        tagRepository.save(tagEntity);
        return tagDto;
    }

    public void update(Long id, TagDto tagDto) {
            Tag updatedTag = tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException(id));
            updatedTag.setName(tagDto.getName());
            tagRepository.save(updatedTag);
    }
}
