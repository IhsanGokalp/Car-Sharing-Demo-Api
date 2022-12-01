package com.example.demo.Service.Impl;

import com.example.demo.Entity.Car;
import com.example.demo.Repository.CarRepository;
import com.example.demo.Service.CarService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Car not found by id " + id));
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void delete(Car id) {
        carRepository.delete(id);
    }

}
