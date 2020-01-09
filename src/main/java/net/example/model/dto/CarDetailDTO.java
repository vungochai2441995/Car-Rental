package net.example.model.dto;

import lombok.Data;

@Data
public class CarDetailDTO {
    private String name;
    private String showroomName;
    private String url;
    private int deposit;
    private int fuel;
    private int consume;
}
