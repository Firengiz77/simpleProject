package com.bookstore.simpleblog.service;

import com.bookstore.simpleblog.dto.SliderDto;
import com.bookstore.simpleblog.dto.request.SliderRequest;
import com.bookstore.simpleblog.mapper.SliderMapper;
import com.bookstore.simpleblog.model.Image;
import com.bookstore.simpleblog.repository.SliderRepository;
import com.bookstore.simpleblog.exceptions.BlogNotFoundException;
import com.bookstore.simpleblog.model.Slider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SliderService {
    private final SliderRepository sliderRepository;
    private final SliderMapper sliderMapper;
    private final ImageService imageService;

    public List<SliderDto> getAll() {
        return sliderMapper.toDto(sliderRepository.findAll());
    }

    public SliderDto getSliderById(Long id) {
        var sliderDto = sliderRepository.findById(id).orElseThrow(() -> new BlogNotFoundException(id));
            return sliderMapper.toDto(sliderDto);
    }

    public SliderDto create(SliderRequest sliderRequest) {
        Image image1 = imageService.create(sliderRequest.getImage());

        Slider slider = Slider.builder()
                .image(image1)
                .title(sliderRequest.getTitle())
                .description(sliderRequest.getDescription())
                .buttonText(sliderRequest.getButtonText())
                .link(sliderRequest.getLink())
                .build();

         return  sliderMapper.toDto(slider);
    }

    public void update(Long id, SliderDto sliderDto) {
            Slider updatedSlider = sliderRepository.findById(id).orElseThrow(() -> new BlogNotFoundException(id));
            updatedSlider.setTitle(sliderDto.getTitle());
            updatedSlider.setDescription(sliderDto.getDescription());
            updatedSlider.setLink(sliderDto.getLink());
            updatedSlider.setButtonText(sliderDto.getButtonText());
    }

    public HttpStatus delete(Long id) {
        if(sliderRepository.existsById(id)){
            sliderRepository.deleteById(id);
            return HttpStatus.OK;
        }
        else{
            return HttpStatus.NOT_FOUND;
        }
    }
}
