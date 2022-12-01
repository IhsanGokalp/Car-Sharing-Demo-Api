package com.example.demo.Service;

import com.example.demo.Entity.User;

import java.util.Optional;

public interface UserService {
    User findById(Long id);

    User save(User user);

    void delete(User id);
}
