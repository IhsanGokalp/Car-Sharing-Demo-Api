package com.example.demo.Service;

import com.example.demo.Entity.Contact;

import java.util.Optional;

public interface ContactService {
    Optional<Contact> findById(Long id);
    Contact save(Contact contact);
}
