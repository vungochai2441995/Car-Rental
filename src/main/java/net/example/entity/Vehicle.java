package net.example.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "vehicle")
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(columnDefinition = "TEXT",name = "image",nullable = false)
    private String url;

    @Column(columnDefinition = "boolean", nullable = false,name = "possible")
    private Boolean possible;

    @Column(columnDefinition = "int", name = "type",nullable = false)
    private int type;

    @Column(name = "startDate",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticketID")
    private Ticket tickets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showRoomID")
    private Showroom showRoom;

    @OneToOne(mappedBy = "vehicle")
    private Bike bike;

    @OneToOne(mappedBy = "vehicle")
    private Bike car;
}
