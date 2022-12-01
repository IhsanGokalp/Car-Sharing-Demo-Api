package com.example.demo.Dto;

import com.example.demo.Entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarCreateDto {

    private String licence;
    private String make;
    private String model;
    private Integer available_seats;
    private Long user_id;
}
