package net.example.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "location")
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;

//    @OneToMany(mappedBy = "location")
////    @Fetch(value = FetchMode.JOIN)
//////    private List<Showroom> showrooms = new ArrayList<>();

    //    @OneToMany(mappedBy = "showroom")
//    private List<Car> cars = new ArrayList<>();
//
//    @OneToMany(mappedBy = "showroom")
//    private List<Bike> bikes = new ArrayList<>();
}
