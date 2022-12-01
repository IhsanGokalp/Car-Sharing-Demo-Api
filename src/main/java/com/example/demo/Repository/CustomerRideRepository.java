package com.example.demo.Repository;

import com.example.demo.Entity.CustomerRide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRideRepository extends JpaRepository<CustomerRide, Long> {
}
