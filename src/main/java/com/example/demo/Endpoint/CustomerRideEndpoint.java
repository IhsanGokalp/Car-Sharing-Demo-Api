package com.example.demo.Endpoint;

import com.example.demo.Dto.ConfirmationCreateDto;
import com.example.demo.Dto.ConfirmationDto;
import com.example.demo.Dto.CustomerRideCreateDto;
import com.example.demo.Dto.CustomerRideUpdateDto;
import com.example.demo.Entity.CustomerRide;
import com.example.demo.Entity.RiderRide;

import java.util.List;

public interface CustomerRideEndpoint {
    CustomerRide findById(Long id);

    CustomerRide save(CustomerRideCreateDto customerRideCreateDto);

    CustomerRide update(Long id, CustomerRideUpdateDto customerRideupdateDto);

    void delete(Long id);

    ConfirmationDto request(Long id, ConfirmationCreateDto confirmationCreateDto);

    List<RiderRide> search(Long id, Double distance, Integer r);

    void deleteRequest(Long id);
}
