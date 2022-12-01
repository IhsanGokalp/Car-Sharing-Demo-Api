package com.example.demo.Endpoint;

import com.example.demo.Dto.UserCreateDto;
import com.example.demo.Dto.UserUpdateDto;
import com.example.demo.Entity.User;

public interface UserEndpoint {
    User findById(Long id);

    User save(UserCreateDto userCreateDto);

    User update(Long id, UserUpdateDto userUpdateDto);

    void delete(Long id);
}
