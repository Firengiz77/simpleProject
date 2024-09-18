package com.bookstore.simpleblog.service;

import com.bookstore.simpleblog.dto.FaqDto;
import com.bookstore.simpleblog.exceptions.FaqNotFoundException;
import com.bookstore.simpleblog.mapper.FaqMapper;
import com.bookstore.simpleblog.model.Faq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bookstore.simpleblog.repository.FaqRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;
    private final FaqMapper faqMapper;

    public List<FaqDto> getAll() {
        return faqMapper.toDto(faqRepository.findAll());
    }

    public FaqDto getFaqById(Long id) {
        var faqDto = faqRepository.findById(id)
                .orElseThrow(() -> new FaqNotFoundException(id));
        return faqMapper.toDto(faqDto);
    }

    public void delete(Long id) {
        if (!faqRepository.existsById(id)) {
            throw new FaqNotFoundException(id);
        }
        faqRepository.deleteById(id);
    }

    public FaqDto create(FaqDto faqDto) {
        var faqEntity = faqMapper.toEntity(faqDto);
        faqRepository.save(faqEntity);
        return faqDto;
    }

    public void update(Long id, FaqDto faqDto) {
            Faq updatedFaq = faqRepository.findById(id).orElseThrow(() -> new FaqNotFoundException(id));
            updatedFaq.setQuestion(faqDto.getQuestion());
            updatedFaq.setAnswer(faqDto.getAnswer());
            faqRepository.save(updatedFaq);
    }
}
