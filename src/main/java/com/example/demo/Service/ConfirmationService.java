package com.example.demo.Service;

import com.example.demo.Entity.Confirmation;
import com.example.demo.Entity.CustomerRide;
import com.example.demo.Entity.RiderRide;
import com.example.demo.Entity.User;

import java.util.List;

public interface ConfirmationService {
    Confirmation requestSave(Confirmation confirmation);
    Confirmation findByRiderRideAndCustomerRide(RiderRide riderRide, CustomerRide customerRide);

    List<Confirmation> findByRiderId(RiderRide id);

    void delete(Confirmation id);

    List<Confirmation> findAllByCustomerRide(CustomerRide customerRide);
    Confirmation findById(Long id);
}
