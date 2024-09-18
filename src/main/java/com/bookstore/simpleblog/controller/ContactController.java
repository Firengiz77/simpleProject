package com.bookstore.simpleblog.controller;

import com.bookstore.simpleblog.dto.ContactDto;
import com.bookstore.simpleblog.service.ContactService;
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
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public List<ContactDto> getAll() {
        return contactService.getAll();
    }

    @GetMapping("/{id}")
    public ContactDto getContactById(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

    @PostMapping
    public ContactDto create(ContactDto contact) {
        return contactService.create(contact);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       ContactDto contact) {
      contactService.update(id, contact);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        contactService.delete(id);
        return HttpStatus.NO_CONTENT;
    }
}
