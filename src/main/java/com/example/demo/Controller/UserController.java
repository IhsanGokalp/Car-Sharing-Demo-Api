package com.example.demo.Controller;

import com.example.demo.Dto.UserCreateDto;
import com.example.demo.Dto.UserUpdateDto;
import com.example.demo.Endpoint.UserEndpoint;
import com.example.demo.Entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserEndpoint userEndpoint;

    public UserController(UserEndpoint userEndpoint) {
        this.userEndpoint = userEndpoint;
    }

    @GetMapping("/{id}")
    private User findById(@PathVariable Long id) {
        return userEndpoint.findById(id);
    }

    @PostMapping
    private User save(@RequestBody UserCreateDto userCreateDto) {
        return userEndpoint.save(userCreateDto);
    }

    @PutMapping("/{id}")
    private User update(@RequestBody UserUpdateDto userUpdateDto, @PathVariable Long id) {
        return userEndpoint.update(id, userUpdateDto);
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable Long id) {
        userEndpoint.delete(id);
    }
}
