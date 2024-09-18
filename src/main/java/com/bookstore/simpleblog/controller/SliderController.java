package com.bookstore.simpleblog.controller;

import com.bookstore.simpleblog.dto.SliderDto;
import com.bookstore.simpleblog.dto.request.SliderRequest;
import com.bookstore.simpleblog.service.SliderService;
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
@RequestMapping("/slider")
@RequiredArgsConstructor
public class SliderController {

    private final SliderService sliderService;

    @GetMapping
    public List<SliderDto> getAll() {
        return sliderService.getAll();
    }

    @GetMapping("/{id}")
    public SliderDto getSliderById(@PathVariable Long id) {
        return sliderService.getSliderById(id);
    }

    @PostMapping
    public SliderDto create(SliderRequest sliderRequest) {
        return sliderService.create(sliderRequest);

    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,SliderDto slider) {
       sliderService.update(id, slider);

    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
       return sliderService.delete(id);
    }
}
