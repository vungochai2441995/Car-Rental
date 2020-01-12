package net.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "ticket")
@Entity

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "createdDate",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;


    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "car_id",referencedColumnName = "id")
    private Car car;

    @ManyToOne()
    @JoinColumn(name = "bike_id",referencedColumnName = "id")
    private Bike bike;
}
