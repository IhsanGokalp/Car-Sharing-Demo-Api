package com.example.demo.Service;

import com.example.demo.Entity.Car;


public interface CarService {
    Car findById(Long id);

    Car save(Car car);

    void delete(Car id);
}
