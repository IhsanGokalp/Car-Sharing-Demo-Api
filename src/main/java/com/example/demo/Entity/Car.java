package com.example.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "cars")
public class Car extends BaseEntity {
    @Column(name = "licence")
    private String licence;
    @Column(name = "make")
    private String make;
    @Column(name = "model")
    private String model;
    @Column(name = "available_seats")
    private Integer available_seats;

    @ManyToOne(/*cascade = CascadeType.ALL fetch = FetchType.LAZY*/)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
