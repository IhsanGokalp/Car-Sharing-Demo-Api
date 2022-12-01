package com.example.demo.Endpoint;

import com.example.demo.Dto.ConfirmationDto;
import com.example.demo.Dto.ConfirmationResponseDto;
import com.example.demo.Dto.RiderRideCreateDto;
import com.example.demo.Dto.RiderRideUpdateDto;
import com.example.demo.Entity.Confirmation;
import com.example.demo.Entity.RiderRide;

import java.util.List;

public interface RiderRideEndpoint {
    RiderRide findById(Long id);

    RiderRide save(RiderRideCreateDto riderRideEndpoint);

    RiderRide update(Long id, RiderRideUpdateDto riderRideUpdateDto);

    void delete(Long id);

    ConfirmationDto response(Long id, ConfirmationResponseDto confirmationResponseDto);

    List<Confirmation> findRequestsById(Long id);
}
