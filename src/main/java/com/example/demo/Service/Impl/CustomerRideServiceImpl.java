package com.example.demo.Service.Impl;

import com.example.demo.Entity.CustomerRide;
import com.example.demo.Repository.CustomerRideRepository;
import com.example.demo.Service.CustomerRideService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CustomerRideServiceImpl implements CustomerRideService {

    private final CustomerRideRepository customerRideRepository;

    public CustomerRideServiceImpl(CustomerRideRepository customerRideRepository) {
        this.customerRideRepository = customerRideRepository;
    }

    @Override
    public CustomerRide findById(Long id) {
        return customerRideRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException());
    }

    @Override
    public CustomerRide save(CustomerRide customerRideCreateDto) {
        return customerRideRepository.save(customerRideCreateDto);
    }

    @Override
    public void delete(CustomerRide id) {
        customerRideRepository.delete(id);
    }
}
