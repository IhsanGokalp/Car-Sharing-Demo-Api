package com.example.demo.Service.Impl;

import com.example.demo.Endpoint.Impl.UserEndpointImpl;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserEndpointImpl.class);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).
                orElseThrow(()-> new EntityNotFoundException("No user found by id "+ id));
    }

    @Override
    public User save(User user) {
//        logger.info("Used");
        return userRepository.save(user);
    }

    @Override
    public void delete(User id) {
        userRepository.delete(id);
    }
}
