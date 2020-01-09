package net.example.model.dto;

import lombok.Data;

@Data
public class BikeSearchDTO {
    private String name;
    private int gear;
    private int price;
    private long location;
    private Long id;
    private String image;
    private String showroomName;
}
