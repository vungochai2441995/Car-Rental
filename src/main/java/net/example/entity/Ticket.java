package net.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "ticket")
@Entity

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    private User users;

//    @OneToMany(mappedBy = "tickets")
//    private List<Car> cars = new ArrayList<>();
//
//    @OneToMany(mappedBy = "tickets")
//    private List<Bike> bikes = new ArrayList<>();
}
