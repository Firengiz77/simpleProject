package com.bookstore.simpleblog.service;

import com.bookstore.simpleblog.dto.AboutDto;
import com.bookstore.simpleblog.dto.request.AboutRequest;
import com.bookstore.simpleblog.exceptions.AboutNotFoundException;
import com.bookstore.simpleblog.mapper.AboutMapper;
import com.bookstore.simpleblog.model.About;
import com.bookstore.simpleblog.model.Image;
import com.bookstore.simpleblog.repository.AboutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AboutService {
    private final AboutRepository aboutRepository;
    private final AboutMapper aboutMapper;
    private final ImageService imageService;

    public List<AboutDto> getAbout() {
        return aboutMapper.toDto(aboutRepository.findAll());
    }

    public AboutDto getAboutById(Long id) {
        var aboutDto = aboutRepository.findById(id).orElseThrow(() -> new AboutNotFoundException(id));
        return aboutMapper.toDto(aboutDto);
    }

    public AboutDto saveAbout(AboutRequest aboutRequest) {
        Image image1 = imageService.create(aboutRequest.getImage());
       About about = About.builder()
               .title(aboutRequest.getTitle())
               .content(aboutRequest.getContent())
               .image(image1)
               .build();
        return aboutMapper.toDto(aboutRepository.save(about));
    }

    public void updateAbout(Long id,AboutRequest aboutRequest) throws IOException {
        About about = aboutRepository.findById(id).orElseThrow(() -> new AboutNotFoundException(id));
        about.setTitle(aboutRequest.getTitle());
        about.setContent(aboutRequest.getContent());
        Image image1 = imageService.update(about.getImage().getId(), aboutRequest.getImage());
        about.setImage(image1);
        aboutRepository.save(about);
    }

    public HttpStatus deleteAbout(Long id) {
        if (aboutRepository.existsById(id)) {
            aboutRepository.deleteById(id);
            return HttpStatus.OK;
        }else{
            return HttpStatus.NOT_FOUND;
        }
    }
}
