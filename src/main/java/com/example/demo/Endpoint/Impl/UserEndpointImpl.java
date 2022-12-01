package com.example.demo.Endpoint.Impl;

import com.example.demo.Config.Checks;
import com.example.demo.Dto.UserCreateDto;
import com.example.demo.Dto.UserUpdateDto;
import com.example.demo.Endpoint.ContactEndpoint;
import com.example.demo.Endpoint.UserEndpoint;
import com.example.demo.Entity.Contact;
import com.example.demo.Entity.User;
import com.example.demo.Service.ContactService;
import com.example.demo.Service.RiderRideService;
import com.example.demo.Service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserEndpointImpl implements UserEndpoint {

    private final UserService userService;
    private final ContactService contactService;
    private final ModelMapper modelMapper;
    private final RiderRideService riderRideService;
    private static final Logger logger = LoggerFactory.getLogger(UserEndpointImpl.class);
    public UserEndpointImpl(UserService userService, ContactEndpoint contactEndpoint, ContactService contactService, ModelMapper modelMapper, RiderRideService riderRideService) {
        this.userService = userService;
        this.contactService = contactService;
        this.modelMapper = modelMapper;
        this.riderRideService = riderRideService;
    }

    @Override
    public User findById(Long id) {
        return Checks.checkEntityExists(userService.findById(id), "No User found by id " + id);
    }

    @Override
    public User save(UserCreateDto userCreateDto) {
        User user = this.modelMapper.map(userCreateDto, User.class);
        /*logger.info(user.toString());

        User user1 = new User();
        logger.info(user1.toString());
         */

        Contact contact = modelMapper.map(userCreateDto, Contact.class);
        contact.setEmail(userCreateDto.getContactDto().getEmail());
        contact.setPhoneNumber(userCreateDto.getContactDto().getPhoneNumber());
        logger.info(contact.toString());
        contactService.save(contact);
//        return userService.save(user);
        return null;
    }

    @Override
    public User update(Long id, UserUpdateDto userUpdateDto) {
        User user = Checks.checkEntityExists(userService.findById(id), "No User with id " + id);
        modelMapper.map(userUpdateDto, user);
        return userService.save(user);
    }

    @Override
    public void delete(Long id) {
        riderRideService.findAllByRider(findById(id)).stream()
                .forEach(x-> riderRideService.delete(x));
        userService.delete(findById(id));
    }
}
