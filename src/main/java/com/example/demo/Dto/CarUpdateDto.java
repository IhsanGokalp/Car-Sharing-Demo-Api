package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarUpdateDto {
    private String licence;
    private String make;
    private String model;
    private Integer available_seats;
}
