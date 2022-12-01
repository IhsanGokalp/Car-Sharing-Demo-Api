package com.example.demo.Controller;

import com.example.demo.Dto.ConfirmationCreateDto;
import com.example.demo.Dto.ConfirmationDto;
import com.example.demo.Dto.CustomerRideCreateDto;
import com.example.demo.Dto.CustomerRideUpdateDto;
import com.example.demo.Endpoint.CustomerRideEndpoint;
import com.example.demo.Entity.CustomerRide;
import com.example.demo.Entity.RiderRide;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customerrides")
public class CustomerRideController {
    private final CustomerRideEndpoint customerRideEndpoint;

    public CustomerRideController(CustomerRideEndpoint customerRideEndpoint) {
        this.customerRideEndpoint = customerRideEndpoint;
    }
    @GetMapping("/{id}")
    private CustomerRide findById(@PathVariable Long id) {
        return customerRideEndpoint.findById(id);
    }

    @PostMapping
    private CustomerRide save(@RequestBody CustomerRideCreateDto customerRideCreateDto){
        return customerRideEndpoint.save(customerRideCreateDto);
    }
    @PutMapping("/{id}")
    private CustomerRide update(@PathVariable Long id, @RequestBody CustomerRideUpdateDto customerRideupdateDto) {
        return customerRideEndpoint.update(id, customerRideupdateDto);
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable Long id) {
        customerRideEndpoint.delete(id);
    }

    @PostMapping("/request/{id}")
    private ConfirmationDto request(@PathVariable Long id, @RequestBody ConfirmationCreateDto confirmationCreateDto) {
        return customerRideEndpoint.request(id, confirmationCreateDto);
    }

    @GetMapping("/search/{id}/{distance}/{r}")
    private List<RiderRide> search(@PathVariable Long id,
                                   @PathVariable Double distance,
                                   @PathVariable Integer r) {
        return customerRideEndpoint.search(id, distance, r);
    }

    @DeleteMapping("/request/{id}")
    private void deleteRequest(@PathVariable Long id) {
        customerRideEndpoint.deleteRequest(id);
    }
}
