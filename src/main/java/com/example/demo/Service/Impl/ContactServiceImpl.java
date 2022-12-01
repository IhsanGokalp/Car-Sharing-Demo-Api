package com.example.demo.Service.Impl;

import com.example.demo.Entity.Contact;
import com.example.demo.Repository.ContactRepository;
import com.example.demo.Service.ContactService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }
}
