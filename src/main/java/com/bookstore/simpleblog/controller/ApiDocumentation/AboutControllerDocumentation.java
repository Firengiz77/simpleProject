package com.bookstore.simpleblog.controller.ApiDocumentation;

import com.bookstore.simpleblog.dto.AboutDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "About", description = "About management APIs")
public interface AboutControllerDocumentation {

    @Operation(
            summary = "Retrieve a About by Id",
            description = "Get a About object by specifying its id. The response is About object with id, title, " +
                    "description and published status.",
            tags = {"abouts", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AboutDto.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/{id}")
    AboutDto getAboutById(@PathVariable Long id);
}
