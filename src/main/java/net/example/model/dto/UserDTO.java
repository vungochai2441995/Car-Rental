package net.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String phone;
    private String email;
}
