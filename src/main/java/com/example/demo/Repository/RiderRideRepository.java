package com.example.demo.Repository;

import com.example.demo.Entity.Car;
import com.example.demo.Entity.Ride;
import com.example.demo.Entity.RiderRide;
import com.example.demo.Entity.User;
import com.vividsolutions.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiderRideRepository extends JpaRepository<RiderRide, Long>,
        PagingAndSortingRepository<RiderRide, Long> {

    @Query(value = "select * from rider_rides where ST_DWithin(cast(from_ as geography), :p1, :distance_from) " +
            "and ST_DWithin(cast(to_ as geography), :p2, :distance_to) limit 10 offset :r", nativeQuery = true)
    List<RiderRide> findNearWithinDistance(Point p1, Point p2,
                                           Double distance_from, Double distance_to,
                                           Integer r);

    List<RiderRide> findAllByCar(Car id);
    List<RiderRide> findAllByRider(User use);
}
