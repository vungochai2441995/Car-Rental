package net.example.model.dto;

import lombok.Data;

@Data
public class BikeDetailDTO {
    private Long id;
    private String name;
    private String showroom;
    private String url;
    private int deposit;
    private int cc;
}
