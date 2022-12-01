package com.example.demo.Endpoint.Impl;


import com.example.demo.Config.Checks;
import com.example.demo.Dto.ContactUpdateDto;
import com.example.demo.Endpoint.ContactEndpoint;
import com.example.demo.Entity.Contact;
import com.example.demo.Service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ContactEndpointImpl implements ContactEndpoint {

    private final ContactService contactService;

    private final ModelMapper modelMapper;
    public ContactEndpointImpl(ContactService contactService, ModelMapper modelMapper) {
        this.contactService = contactService;
        this.modelMapper = modelMapper;
    }


    @Override
    public Contact findById(Long id) {
        return Checks.checkEntityExists(contactService.findById(id),
                "No contact found by id " + id).get();
    }

    @Override
    public Contact update(Long id, ContactUpdateDto contactUpdateDto) {
        Contact contact = Checks.checkEntityExists(contactService.findById(id), "No Contact with id "+ id).get();
        modelMapper.map(contactUpdateDto, contact);
        return contactService.save(contact);
    }
}
