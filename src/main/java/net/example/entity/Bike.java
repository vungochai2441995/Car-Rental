package net.example.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "bike")
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license",nullable = false,unique = true)
    private String license;

    @Column(name = "deposit",nullable = false)
    private int deposit;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name="catalog",nullable = false)
    private String cata;

    @Column(name="gear",nullable = false)
    private int gear;


    @Column(name="cc",nullable = false)
    private int cc;

    @Column(name="price",nullable = false)
    private int price;

    @Column(columnDefinition = "TEXT",name = "image",nullable = false)
    private String url;

    @Column(columnDefinition = "boolean", nullable = false,name = "possible")
    private Boolean possible;

    @Column(name = "showroom",nullable = false)
    private String showroom;

    @Column(name = "endDate",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate  ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "locationID")
    private Location location;
}
