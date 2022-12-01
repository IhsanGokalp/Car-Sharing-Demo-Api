package com.example.demo.Dto;

import com.example.demo.Entity.CustomerRide;
import com.example.demo.Entity.RiderRide;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmationDto {
    private RiderRide riderRide;

    private CustomerRide customerRide;

    private Boolean approved;
}
