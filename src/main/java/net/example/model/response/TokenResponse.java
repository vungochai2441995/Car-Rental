package net.example.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.example.model.dto.UserDTO;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class TokenResponse {
    private String message;
    private String token;
    private HttpStatus statusCode;
    private UserDTO userDTO;
}
