package com.example.demo.Repository;

import com.example.demo.Entity.Confirmation;
import com.example.demo.Entity.CustomerRide;
import com.example.demo.Entity.RiderRide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {
    Confirmation findByRiderRideAndCustomerRide(RiderRide riderRide, CustomerRide customerRide);
    List<Confirmation> findByRiderRide(RiderRide riderRide);
    List<Confirmation> findAllByCustomerRide(CustomerRide customerRide);
}
