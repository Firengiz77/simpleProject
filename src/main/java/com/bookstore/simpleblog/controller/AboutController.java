package com.bookstore.simpleblog.controller;

import com.bookstore.simpleblog.controller.ApiDocumentation.AboutControllerDocumentation;
import com.bookstore.simpleblog.dto.AboutDto;
import com.bookstore.simpleblog.dto.request.AboutRequest;
import com.bookstore.simpleblog.service.AboutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/about")
@RequiredArgsConstructor
public class AboutController implements AboutControllerDocumentation {

    private final AboutService aboutService;

    @GetMapping
    public List<AboutDto> getAbout() {
        return aboutService.getAbout();
    }

    @Override
    @GetMapping("/{id}")
    public AboutDto getAboutById(@PathVariable Long id) {
        return aboutService.getAboutById(id);
    }

    @PostMapping
    public AboutDto saveAbout(AboutRequest aboutRequest) {
        return aboutService.saveAbout(aboutRequest);
    }

    @PutMapping("/{id}")
    public void updateAbout(@PathVariable Long id,AboutRequest aboutRequest) throws IOException {
        aboutService.updateAbout(id, aboutRequest);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteAbout(@PathVariable Long id) {
     return aboutService.deleteAbout(id);

    }
}
