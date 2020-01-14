package net.example.model.response;

import lombok.Data;
import net.example.model.dto.TicketDTO;
import org.springframework.http.HttpStatus;

@Data
public class BookTicketResponse {
    private String message;
    private HttpStatus status;
}
