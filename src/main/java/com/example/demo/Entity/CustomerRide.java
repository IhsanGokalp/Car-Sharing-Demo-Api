package com.example.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "customer_rides")
public class CustomerRide extends Ride{
    @Column(name = "amount")
    private Integer amount;

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name = "rider_ride_id", referencedColumnName = "id")
    private RiderRide riderRide;

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private User customer;
}
