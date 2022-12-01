package com.example.demo.Service.Impl;

import com.example.demo.Entity.Confirmation;
import com.example.demo.Entity.CustomerRide;
import com.example.demo.Entity.RiderRide;
import com.example.demo.Entity.User;
import com.example.demo.Repository.ConfirmationRepository;
import com.example.demo.Service.ConfirmationService;
import com.example.demo.Service.RiderRideService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ConfirmationServiceImpl implements ConfirmationService {

    private final ConfirmationRepository confirmationRepository;

    private final RiderRideService riderRideService;
    public ConfirmationServiceImpl(ConfirmationRepository confirmationRepository, RiderRideService riderRideService) {
        this.confirmationRepository = confirmationRepository;
        this.riderRideService = riderRideService;
    }


    @Override
    public Confirmation requestSave(Confirmation confirmation) {
        return confirmationRepository.save(confirmation);
    }

    @Override
    public Confirmation findByRiderRideAndCustomerRide(RiderRide riderRide, CustomerRide customerRide) {
        return confirmationRepository.findByRiderRideAndCustomerRide(riderRide, customerRide);
    }

    @Override
    public List<Confirmation> findByRiderId(RiderRide id) {
        return confirmationRepository.findByRiderRide(id);
    }

    @Override
    public void delete(Confirmation id) {
        if (id.getApproved()) {
            id.getRiderRide().setAvailable(id.getRiderRide().getAvailable()
                    + id.getCustomerRide().getAmount());
            id.getRiderRide().setOccupied(id.getRiderRide().getOccupied()
                    - id.getCustomerRide().getAmount());
            riderRideService.save(id.getRiderRide());
        }
        confirmationRepository.delete(id);
    }

    @Override
    public List<Confirmation> findAllByCustomerRide(CustomerRide customerRide) {
        return confirmationRepository.findAllByCustomerRide(customerRide);
    }


    @Override
    public Confirmation findById(Long id) {
        return confirmationRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());
    }
}
