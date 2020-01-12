package net.example.model.mapper;

import net.example.dao.TicketDAO;
import net.example.entity.Ticket;
import net.example.entity.User;
import net.example.model.dto.TicketDTO;
import net.example.model.dto.UserDTO;

public class ProductMapper {
    public static TicketDTO toTicketDTO(Ticket ticket){
        User user = ticket.getUser();
        UserDTO userDTO = UserMapper.toUserDTO(user);
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setCreatedDate(ticket.getCreatedDate());
        ticketDTO.setEndDate(ticket.getEndDate());
        ticketDTO.setId(ticket.getId());
        ticketDTO.setBike(ticket.getBike());
        ticketDTO.setCar(ticket.getCar());
        ticketDTO.setUser(userDTO);
        return ticketDTO;
    }
}
