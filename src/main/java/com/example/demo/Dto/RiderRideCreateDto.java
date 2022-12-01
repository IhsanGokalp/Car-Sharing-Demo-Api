package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;

import java.util.Date;

@Getter
@Setter
public class RiderRideCreateDto {
    private String from;
    private String to;
    private Date date;
    private Integer available_seats;
    private Integer occupied;
    private Long user_id;
    private Long car_id;
}
