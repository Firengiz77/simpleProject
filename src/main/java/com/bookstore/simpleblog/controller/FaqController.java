package com.bookstore.simpleblog.controller;



import com.bookstore.simpleblog.dto.FaqDto;
import com.bookstore.simpleblog.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/faq")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    @GetMapping
    public List<FaqDto> getFaq() {
        return faqService.getAll();
    }

    @GetMapping("/{id}")
    public FaqDto getFaqById(@PathVariable Long id) {
        return faqService.getFaqById(id);
    }

    @PostMapping
    public ResponseEntity<FaqDto> createFaq( FaqDto faq) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(faqService.create(faq));
    }

    @PutMapping("/{id}")
    public void updateFaq(@PathVariable Long id, FaqDto faqDto) {
        faqService.update(id, faqDto);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteFaq(@PathVariable Long id) {
        faqService.delete(id);
        return HttpStatus.NO_CONTENT;
    }
}
