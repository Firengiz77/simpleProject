package com.bookstore.simpleblog.controller;

import com.bookstore.simpleblog.controller.ApiDocumentation.VideoControllerDocumentation;
import com.bookstore.simpleblog.dto.VideoDto;
import com.bookstore.simpleblog.dto.request.VideoRequest;
import com.bookstore.simpleblog.model.Image;
import com.bookstore.simpleblog.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController implements VideoControllerDocumentation {

    private final VideoService videoService;

    @GetMapping
    public List<VideoDto> getVideos() {
        return videoService.getVideos();
    }

    @Override
    @GetMapping("/{id}")
    public VideoDto getVideo(@PathVariable Long id) {
        return videoService.getVideo(id);
    }


    @Override
    @PostMapping
    public VideoDto createVideo(VideoRequest videoRequest) {
        return videoService.createVideo(videoRequest);
    }

    @Override
    @PutMapping("/{id}")
    public VideoDto updateVideo(@PathVariable Long id, VideoRequest videoRequest) throws IOException {
       return videoService.updateVideo(id, videoRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public HttpStatus deleteVideo(@PathVariable Long id) {
        return videoService.deleteVideo(id);
    }

    @GetMapping("/getImage/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable Long id) {
        return videoService.getImage(id);
    }

}
