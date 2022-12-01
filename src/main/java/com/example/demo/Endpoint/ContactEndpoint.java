package com.example.demo.Endpoint;

import com.example.demo.Dto.ContactUpdateDto;
import com.example.demo.Entity.Contact;

public interface ContactEndpoint {
    Contact findById(Long id);

    Contact update(Long id, ContactUpdateDto contactUpdateDto);
}
