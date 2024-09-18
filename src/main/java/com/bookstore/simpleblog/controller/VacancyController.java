package com.bookstore.simpleblog.controller;

import com.bookstore.simpleblog.dto.VacancyDto;
import com.bookstore.simpleblog.service.VacancyService;
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
@RequestMapping("/vacancy")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;

    @GetMapping
    public List<VacancyDto> getVacancies(){
        return vacancyService.getVacancies();
    }

    @GetMapping("/{id}")
    public VacancyDto getVacancy(@PathVariable Long id){
        return vacancyService.getVacancy(id);
    }

    @PostMapping
    public VacancyDto createVacancy(VacancyDto vacancyDto) {
        return vacancyService.createVacancy(vacancyDto);
    }

    @PutMapping("/{id}")
    public void updateVacancy(@PathVariable Long id,VacancyDto vacancyDto) {
         vacancyService.updateVacancy(id,vacancyDto);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteVacancy(@PathVariable Long id){
       return vacancyService.deleteVacancy(id);
    }
}
