package com.bookstore.simpleblog.service;

import com.bookstore.simpleblog.dto.BlogDto;
import com.bookstore.simpleblog.dto.response.BlogResponse;
import com.bookstore.simpleblog.mapper.BlogMapper;
import com.bookstore.simpleblog.model.Blog;
import com.bookstore.simpleblog.repository.BlogRepository;
import com.bookstore.simpleblog.repository.TagRepository;
import com.bookstore.simpleblog.exceptions.BlogNotFoundException;
import com.bookstore.simpleblog.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final TagRepository tagRepository;
    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;

    public List<BlogDto> getAll() {
        return blogMapper.toDto(blogRepository.findAll());
    }

    public BlogDto getBlogById(Long id) {
        var blogDto = blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException(id));
        return blogMapper.toDto(blogDto);
    }

    public BlogDto create(BlogDto blogDto) {
        var blogEntity = blogMapper.toEntity(blogDto);
         blogRepository.save(blogEntity);
         return blogDto;
    }

    public void update(Long id, BlogDto blogDto) {
        Blog updatedBlog = blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException(id));
        updatedBlog.setDescription(blogDto.getDescription());
        updatedBlog.setTitle(blogDto.getTitle());

        List<Tag> attachedTags = new ArrayList<>();
        for (Tag tag : blogDto.getTags()) {
            Tag existingTag = tagRepository.findByName(tag.getName());
            if (existingTag != null) {
                attachedTags.add(existingTag);
            } else {
                attachedTags.add(tag);
            }
        }
        updatedBlog.setTags(attachedTags);
        blogRepository.save(updatedBlog);

    }

    public void delete(Long id) {
        if (!blogRepository.existsById(id)) {
            throw new BlogNotFoundException(id);
        }
        blogRepository.deleteById(id);
    }

    public List<BlogResponse> getBlogByDescription(String description) {
        return blogRepository.findByDescriptionLike(description);

//        List<Object[]> results = blogRepository.findByDescriptionLike(description);
//        return results.stream()
//                .map(result -> new BlogResponse((Long) result[0], (String) result[1], (String) result[2]))
//                .collect(Collectors.toList());


    }

}
