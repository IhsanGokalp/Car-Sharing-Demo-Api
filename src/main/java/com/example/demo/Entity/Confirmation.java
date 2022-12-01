package com.example.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "confirmations")
public class Confirmation extends BaseEntity{

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name = "rider_ride_id", referencedColumnName = "id")
    private RiderRide riderRide;

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name = "customer_ride_id", referencedColumnName = "id")
    private CustomerRide customerRide;

    @Column(name = "approved")
    private Boolean approved;
}
