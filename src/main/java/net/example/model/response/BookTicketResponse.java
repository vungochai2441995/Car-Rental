package net.example.model.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BookTicketResponse {
    private String message;
    private HttpStatus status;
}