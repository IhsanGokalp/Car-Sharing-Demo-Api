package com.example.demo.Service.Impl;

import com.example.demo.Entity.Car;
import com.example.demo.Entity.RiderRide;
import com.example.demo.Entity.User;
import com.example.demo.Repository.RiderRideRepository;
import com.example.demo.Service.RiderRideService;
import com.vividsolutions.jts.geom.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RiderRideServiceImpl implements RiderRideService {

    private final RiderRideRepository riderRideRepository;
    private static final Logger logger = LoggerFactory.getLogger(RiderRideServiceImpl.class);

    public RiderRideServiceImpl(RiderRideRepository riderRideRepository) {
        this.riderRideRepository = riderRideRepository;
    }

    @Override
    public RiderRide findById(Long id) {
        return riderRideRepository.findById(id).
                orElseThrow(()-> new EntityNotFoundException("No rider ride found by id " + id));
    }

    @Override
    public RiderRide save(RiderRide ride) {
        return riderRideRepository.save(ride);
    }

    @Override
    public void delete(RiderRide byId) {
        riderRideRepository.delete(byId);
    }

    @Override
    public List<RiderRide> findNearWithinDistance(Point p1, Point p2,
                                                  Double distance_from, Double distance_to, Integer i) {
        return riderRideRepository.
                findNearWithinDistance(p1, p2, distance_from, distance_to, i);
    }

    @Override
    public List<RiderRide> findAllByCar(Car byId) {
        return riderRideRepository.findAllByCar(byId);
    }

    @Override
    public List<RiderRide> findAllByRider(User byId) {
        return riderRideRepository.findAllByRider(byId);
    }
}
