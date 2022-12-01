package com.example.demo.Service;

import com.example.demo.Entity.CustomerRide;

import java.util.Optional;

public interface CustomerRideService {
    CustomerRide findById(Long id);

    CustomerRide save(CustomerRide customerRideCreateDto);

    void delete(CustomerRide id);
}
