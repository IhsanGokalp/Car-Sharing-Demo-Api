package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserCreateDto {
    private String firstName;
    private String lastName;
    private Date dob;
    private ContactDto contactDto;
}
