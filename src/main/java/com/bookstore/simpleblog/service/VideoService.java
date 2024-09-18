package com.bookstore.simpleblog.service;

import com.bookstore.simpleblog.dto.VideoDto;
import com.bookstore.simpleblog.dto.request.VideoRequest;
import com.bookstore.simpleblog.exceptions.ImageNotFoundException;
import com.bookstore.simpleblog.exceptions.VideoNotFoundException;
import com.bookstore.simpleblog.mapper.VideoMapper;
import com.bookstore.simpleblog.model.Image;
import com.bookstore.simpleblog.model.Video;
import com.bookstore.simpleblog.repository.ImageRepository;
import com.bookstore.simpleblog.repository.VideoRepository;
import com.bookstore.simpleblog.util.ImageUtil;
import jakarta.transaction.Transactional;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class VideoService {
    private final VideoMapper videoMapper;
    private final VideoRepository videoRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    public VideoService(VideoMapper videoMapper, VideoRepository videoRepository, ImageService imageService, ImageRepository imageRepository) {
        this.videoMapper = videoMapper;
        this.videoRepository = videoRepository;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
    }

    public List<VideoDto> getVideos() {
        return videoMapper.toDto(videoRepository.findAll());
    }

    public VideoDto getVideo(Long id) {
        return videoMapper.toDto(videoRepository.findById(id).orElseThrow(() -> new VideoNotFoundException(id)));
    }

    @Transactional
    public VideoDto createVideo(VideoRequest videoRequest) {
        Image image1 = imageService.create(videoRequest.getImage());

        Video video = Video.builder()
                .name(videoRequest.getName())
                .link(videoRequest.getLink())
                .image(image1)
                .build();

        return videoMapper.toDto(videoRepository.save(video));
    }

    @Transactional
    public VideoDto updateVideo(Long id, VideoRequest videoRequest) throws IOException {
        Video video = videoRepository.findById(id).orElseThrow(() -> new VideoNotFoundException(id));
        video.setName(videoRequest.getName());
        video.setLink(videoRequest.getLink());
        Image image1 = imageService.update(video.getImage().getId(), videoRequest.getImage());
        video.setImage(image1);

        return videoMapper.toDto(videoRepository.save(video));
    }

    public HttpStatus deleteVideo(Long id) {
        if (videoRepository.existsById(id)) {
            videoRepository.deleteById(id);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    public ResponseEntity<Resource> getImage(Long id) {
        Image image1 = imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException(id));
        byte[] imageData = ImageUtil.decompressImage(image1.getFile().getBytes());

        ByteArrayResource resource = new ByteArrayResource(imageData);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + image1.getName());
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(imageData.length)
                .body(resource);
    }
}
