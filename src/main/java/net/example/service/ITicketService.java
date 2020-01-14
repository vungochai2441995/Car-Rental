package net.example.service;

import net.example.model.dto.TicketDTO;
import net.example.model.request.InsertBookingRequest;
import net.example.model.response.BookTicketResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITicketService {
    public BookTicketResponse insertTicket(InsertBookingRequest insertBookingRequest);
    public List<TicketDTO> findAllTicket();
}
