package com.example.demo.Endpoint.Impl;

import com.example.demo.Config.Checks;
import com.example.demo.Dto.CarCreateDto;
import com.example.demo.Dto.CarUpdateDto;
import com.example.demo.Endpoint.CarEndpoint;
import com.example.demo.Entity.Car;
import com.example.demo.Entity.Ride;
import com.example.demo.Entity.RiderRide;
import com.example.demo.Service.CarService;
import com.example.demo.Service.RiderRideService;
import com.example.demo.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarEndpointImpl implements CarEndpoint {

    private final CarService carService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final RiderRideService riderRideService;

    public CarEndpointImpl(CarService carService, ModelMapper modelMapper, UserService userService, RiderRideService riderRideService) {
        this.carService = carService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.riderRideService = riderRideService;
    }

    @Override
    public Car findById(Long id) {
        return Checks.checkEntityExists(carService.findById(id), "No car found by id " + id);
    }

    @Override
    public Car save(CarCreateDto carCreateDto) {
        Car car = this.modelMapper.map(carCreateDto, Car.class);
        car.setUser(Checks.checkEntityExists(userService.findById(carCreateDto.getUser_id()), ""));
        return carService.save(car);
    }

    @Override
    public Car update(Long id, CarUpdateDto carUpdateDto) {
        Car car = carService.findById(id);
        modelMapper.map(carUpdateDto, car);
        return carService.save(car);
    }

    @Override
    public void delete(Long id) {
        List<RiderRide> rides = riderRideService.findAllByCar(findById(id));
        rides.stream().forEach(x-> x.setCar(null));
        rides.stream().forEach(x->riderRideService.save(x));
        carService.delete(findById(id));
    }
}
