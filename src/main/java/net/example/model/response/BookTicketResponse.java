package net.example.model.response;

import lombok.Data;
import net.example.model.dto.TicketDTO;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Data
public class BookTicketResponse {
    private String message;
    private HttpStatus status;
    private Optional vehicle;
}