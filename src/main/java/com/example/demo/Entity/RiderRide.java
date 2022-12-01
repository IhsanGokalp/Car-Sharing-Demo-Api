package com.example.demo.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "rider_rides")
public class RiderRide extends Ride{

    @Column(name = "available")
    private Integer available;

    @Column(name = "occupied")
    private Integer occupied;

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name = "rider_id", referencedColumnName = "id")
    private User rider;

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;
}
