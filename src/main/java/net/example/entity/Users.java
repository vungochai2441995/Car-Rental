package net.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username",unique = true)
    private String username;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name="address")
    private String address;

    @Column(name="pass_word",nullable = false)
    private String password;

    @OneToMany(mappedBy = "users")
    private List<Ticket> tickets = new ArrayList<>();

}