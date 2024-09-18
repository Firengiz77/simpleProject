package com.bookstore.simpleblog.service;

import com.bookstore.simpleblog.dto.VacancyDto;
import com.bookstore.simpleblog.exceptions.VacancyNotFoundException;
import com.bookstore.simpleblog.mapper.VacancyMapper;
import com.bookstore.simpleblog.model.Vacancy;
import com.bookstore.simpleblog.repository.VacancyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyService {
    private final VacancyMapper vacancyMapper;
    private final VacancyRepository vacancyRepository;

    public VacancyService(VacancyMapper vacancyMapper,  VacancyRepository vacancyRepository) {
        this.vacancyMapper = vacancyMapper;
        this.vacancyRepository = vacancyRepository;
    }

    public List<VacancyDto> getVacancies() {
        return vacancyMapper.toDto(vacancyRepository.findAll());
    }

    public VacancyDto getVacancy(Long id) {
        return vacancyMapper.toDto(vacancyRepository.findById(id).orElseThrow(()-> new VacancyNotFoundException(id)));
    }

    public VacancyDto createVacancy(VacancyDto vacancyDto) {
        Vacancy vacancy = vacancyMapper.toEntity(vacancyDto);
        vacancyRepository.save(vacancy);
        return  vacancyDto;
    }

    public void updateVacancy(Long id, VacancyDto vacancyDto) {
       Vacancy vacancy =  vacancyRepository.findById(id).orElseThrow(()-> new VacancyNotFoundException(id));
       vacancy.setDescription(vacancyDto.getDescription());
       vacancy.setTitle(vacancyDto.getTitle());
       vacancy.setSlug(vacancyDto.getSlug());
       vacancyRepository.save(vacancy);
    }

    public HttpStatus deleteVacancy(Long id) {
        if(vacancyRepository.existsById(id)){
            vacancyRepository.deleteById(id);
            return HttpStatus.OK;
        }else{
            return HttpStatus.NOT_FOUND;
        }
    }
}
