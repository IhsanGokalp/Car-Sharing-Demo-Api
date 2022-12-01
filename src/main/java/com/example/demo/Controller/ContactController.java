package com.example.demo.Controller;

import com.example.demo.Dto.ContactUpdateDto;
import com.example.demo.Endpoint.ContactEndpoint;
import com.example.demo.Entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactEndpoint contactEndpoint;

    public ContactController(ContactEndpoint contactEndpoint) {
        this.contactEndpoint = contactEndpoint;
    }

    @GetMapping("/{id}")
    private Contact findById(@PathVariable Long id) {
        return contactEndpoint.findById(id);
    }

    @PutMapping("/{id}")
    private Contact update(@PathVariable Long id, @RequestBody ContactUpdateDto contactUpdateDto) {
        return contactEndpoint.update(id, contactUpdateDto);
    }
}
