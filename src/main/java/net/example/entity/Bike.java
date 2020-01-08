package net.example.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bike")
public class Bike {
    @Id
    private String id;

    @Column(name="catalog",unique = true)
    private String cata;

    @Column(name="gear",nullable = false)
    private int gear;

    @Column(name="fuel",nullable = false)
    private int fuel;

    @Column(name="cc",nullable = false)
    private int cc;

    @Column(name="price",nullable = false)
    private int price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
