package net.example.model.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String address;
    private String phone;
    private String email;
    private String url;
}
