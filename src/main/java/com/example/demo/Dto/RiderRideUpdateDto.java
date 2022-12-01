package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;

import java.util.Date;

@Setter
@Getter
public class RiderRideUpdateDto {
    private String from;
    private String to;
    private Date time;
    private Integer available_seats;
    private Long car_id;
}
