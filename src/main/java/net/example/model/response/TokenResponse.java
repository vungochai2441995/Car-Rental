package net.example.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class TokenResponse {
    private String message;
    private String token;
    private HttpStatus statusCode;
}
