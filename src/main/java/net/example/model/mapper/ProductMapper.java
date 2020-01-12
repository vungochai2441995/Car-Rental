package net.example.model.mapper;

import net.example.dao.TicketDAO;
import net.example.entity.Ticket;
import net.example.model.dto.TicketDTO;

public class ProductMapper {
    public static TicketDTO toTicketDTO(Ticket ticket){
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setCreatedDate(ticket.getCreatedDate());
        ticketDTO.setEndDate(ticket.getEndDate());
        ticketDTO.setId(ticket.getId());
        ticketDTO.setBike(ticket.getBike());
        ticketDTO.setCar(ticket.getCar());
        ticketDTO.setUserID(ticket.getUser().getId());
        return ticketDTO;
    }
}
