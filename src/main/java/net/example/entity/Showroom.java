package net.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "show_room")
@Entity
public class Showroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location",nullable = false)
    private String location;

    @Column(name = "showroom",nullable = false)
    private String name;

    @OneToMany(mappedBy = "showRoom")
    private List<Vehicle> vehicles = new ArrayList<>();
}
