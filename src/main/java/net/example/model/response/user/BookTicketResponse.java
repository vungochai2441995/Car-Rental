package net.example.model.response.user;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BookTicketResponse {
    private String message;
    private HttpStatus status;

}
