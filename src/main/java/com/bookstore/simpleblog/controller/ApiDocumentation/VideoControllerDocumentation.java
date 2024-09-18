package com.bookstore.simpleblog.controller.ApiDocumentation;

import com.bookstore.simpleblog.dto.VideoDto;
import com.bookstore.simpleblog.dto.request.VideoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "Video", description = "Video management APIs")
public interface VideoControllerDocumentation {
    @Operation(
            summary = "Get all videos",
            description = "Get a video object by specifying its id. The response is Video object with name, link, image" )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = VideoDto.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping
    List<VideoDto> getVideos();

    @Operation(
            summary = "Retrieve a Video by Id",
            description = "Get ALL Video object. The response is Video object with name, link, image")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = VideoDto.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/{id}")
    VideoDto getVideo(@PathVariable Long id);


    @Operation(
            summary = "Create Video",
            description = "The response is Created Video object with name, link,image ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = VideoDto.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping
    VideoDto createVideo(VideoRequest videoRequest);

    @Operation(
            summary = "Update Video",
            description = "The response is Updated Video object with name, link, image")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = VideoDto.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PutMapping("/{id}")
    VideoDto updateVideo(@PathVariable Long id,VideoRequest videoRequest) throws IOException;


    @Operation(
            summary = "Delete Video",
            description = "The response is Deleted Video object")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = VideoDto.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @DeleteMapping("/{id}")
    HttpStatus deleteVideo(@PathVariable Long id);

}
