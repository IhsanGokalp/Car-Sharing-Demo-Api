package com.example.demo.Controller;

import com.example.demo.Dto.ConfirmationDto;
import com.example.demo.Dto.ConfirmationResponseDto;
import com.example.demo.Dto.RiderRideCreateDto;
import com.example.demo.Dto.RiderRideUpdateDto;
import com.example.demo.Endpoint.RiderRideEndpoint;
import com.example.demo.Entity.Confirmation;
import com.example.demo.Entity.RiderRide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/riderrides")
public class RiderRideController {
    @Autowired
    private RiderRideEndpoint riderRideEndpoint;

    @GetMapping("/{id}")
    private RiderRide findById(@PathVariable Long id) {
        return riderRideEndpoint.findById(id);
    }

    @PostMapping
    private RiderRide save(@RequestBody RiderRideCreateDto riderRideCreateDto) {
        return riderRideEndpoint.save(riderRideCreateDto);
    }

    @PutMapping("/{id}")
    private RiderRide update(@PathVariable Long id, @RequestBody RiderRideUpdateDto riderRideUpdateDto) {
        return riderRideEndpoint.update(id, riderRideUpdateDto);
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable Long id) {
        riderRideEndpoint.delete(id);
    }

    @PutMapping("/request/{id}")
    private ConfirmationDto response(@PathVariable Long id, @RequestBody
    ConfirmationResponseDto confirmationResponseDto) {
        return riderRideEndpoint.response(id, confirmationResponseDto);
    }

    @GetMapping("/requests/{id}")
    private List<Confirmation> requests(@PathVariable Long id) {
        return riderRideEndpoint.findRequestsById(id);
    }
}
