package com.example.demo.Controller;

import com.example.demo.Dto.CarCreateDto;
import com.example.demo.Dto.CarUpdateDto;
import com.example.demo.Endpoint.CarEndpoint;
import com.example.demo.Entity.Car;
import com.example.demo.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarEndpoint carEndpoint;

    public CarController(CarEndpoint carEndpoint) {
        this.carEndpoint = carEndpoint;
    }

    @GetMapping("/{id}")
    private Car findById(@PathVariable Long id) {
        return carEndpoint.findById(id);
    }

    @PostMapping
    private Car save(@RequestBody CarCreateDto carCreateDto) {
        return carEndpoint.save(carCreateDto);
    }

    @PutMapping("/{id}")
    private Car update(@PathVariable Long id, @RequestBody CarUpdateDto carUpdateDto) {
        return carEndpoint.update(id, carUpdateDto);
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable Long id) {
        carEndpoint.delete(id);
    }
}
