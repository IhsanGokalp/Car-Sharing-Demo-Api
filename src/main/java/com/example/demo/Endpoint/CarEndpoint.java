package com.example.demo.Endpoint;

import com.example.demo.Dto.CarCreateDto;
import com.example.demo.Dto.CarUpdateDto;
import com.example.demo.Entity.Car;

public interface CarEndpoint {
    Car findById(Long id);

    Car save(CarCreateDto carCreateDto);

    Car update(Long id, CarUpdateDto carUpdateDto);

    void delete(Long id);
}
