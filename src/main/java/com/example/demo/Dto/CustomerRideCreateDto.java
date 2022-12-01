package com.example.demo.Dto;

import com.example.demo.Entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;

import java.util.Date;

@Getter
@Setter
public class CustomerRideCreateDto {
    private String from;
    private String to;
    private Date time;
    private Integer amount;
    private Long customer_id;
}
