package com.example.demo.Service;

import com.example.demo.Entity.Car;
import com.example.demo.Entity.RiderRide;
import com.example.demo.Entity.User;
import com.vividsolutions.jts.geom.Point;

import java.util.List;

public interface RiderRideService {
    RiderRide findById(Long id);

    RiderRide save(RiderRide ride);

    void delete(RiderRide byId);
    List<RiderRide> findNearWithinDistance(Point p1, Point p2,
                                           Double distance_from, Double distance_to, Integer i);

    List<RiderRide> findAllByCar(Car byId);

    List<RiderRide> findAllByRider(User byId);
}
