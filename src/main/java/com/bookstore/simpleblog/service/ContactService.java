package com.bookstore.simpleblog.service;


import com.bookstore.simpleblog.dto.ContactDto;
import com.bookstore.simpleblog.mapper.ContactMapper;
import com.bookstore.simpleblog.model.Contact;
import com.bookstore.simpleblog.repository.ContactRepository;
import com.bookstore.simpleblog.exceptions.ContactNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public List<ContactDto> getAll() {
        return contactMapper.toDto(contactRepository.findAll());
    }

    public ContactDto getContactById(Long id) {
        var contactDto = contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
            return contactMapper.toDto(contactDto);
    }

    public void delete(Long id) {
       if (!contactRepository.existsById(id)) {
          throw new ContactNotFoundException(id);
       }
       contactRepository.deleteById(id);
    }

    public ContactDto create(ContactDto contactDto) {
        var contactToEntity = contactMapper.toEntity(contactDto);
        contactRepository.save(contactToEntity);
        return  contactDto;
    }

    public void update(Long id, ContactDto contactDto) {
            Contact updatedContact = contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
            updatedContact.setAddress(contactDto.getAddress());
            updatedContact.setEmail(contactDto.getEmail());
            updatedContact.setPhone(contactDto.getPhone());
            updatedContact.setCountry(contactDto.getCountry());
            updatedContact.setImage(contactDto.getImage());
    }
}
