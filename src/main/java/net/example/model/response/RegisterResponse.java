package net.example.model.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RegisterResponse {
    private String message;
    private HttpStatus status;
}
