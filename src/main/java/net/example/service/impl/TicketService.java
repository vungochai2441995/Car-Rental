package net.example.service.impl;

import net.example.dao.BikeDAO;
import net.example.dao.CarDAO;
import net.example.dao.TicketDAO;
import net.example.dao.UsersDAO;
import net.example.entity.Ticket;
import net.example.entity.User;
import net.example.model.dto.TicketDTO;
import net.example.model.mapper.ProductMapper;
import net.example.model.request.InsertBookingRequest;
import net.example.model.response.BookTicketResponse;
import net.example.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TicketService implements ITicketService {
    @Autowired
    TicketDAO ticketDAO;

    @Autowired
    UsersDAO usersDAO;

    @Autowired
    CarDAO carDAO;

    @Autowired
    BikeDAO bikeDAO;

    @Override
    public List<TicketDTO> findAllTicket() {
        List<Ticket> tickets = ticketDAO.findAll();
        List<TicketDTO> ticketDTOS = new CopyOnWriteArrayList<>();
        for (Ticket ticket : tickets) {
            TicketDTO ticketDTO = ProductMapper.toTicketDTO(ticket);
            ticketDTOS.add(ticketDTO);
        }
        return ticketDTOS;
    }

    @Override
    public BookTicketResponse insertTicket(InsertBookingRequest insertBookingRequest) {
        BookTicketResponse bookTicketResponse = new BookTicketResponse();
        Date start_date = insertBookingRequest.getStartDate();
        Date end_date = insertBookingRequest.getEndDate();
        Long vehicle_id = insertBookingRequest.getVehicle_id();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        User user = new User();
        try {
            user = usersDAO.findByUsername(username);
        } catch (Exception e) {
            bookTicketResponse.setMessage("Can not find user");
            bookTicketResponse.setStatus(HttpStatus.NOT_FOUND);
            bookTicketResponse.setVehicle(null);
            return bookTicketResponse;
        }
        Long userID = user.getId();

        if (insertBookingRequest.getType() == 1) {
            Optional car = carDAO.findById(vehicle_id);
            if (!car.isPresent()) {
                bookTicketResponse.setMessage("No car available");
                bookTicketResponse.setStatus(HttpStatus.NOT_FOUND);
                bookTicketResponse.setVehicle(null);
                return bookTicketResponse;
            } else {
                try {
                    ticketDAO.bookCar(start_date, end_date, vehicle_id, userID);
                    bookTicketResponse.setMessage("Book car success");
                    bookTicketResponse.setStatus(HttpStatus.OK);
                    bookTicketResponse.setVehicle(car);
                    return bookTicketResponse;
                } catch (Exception e) {
                    bookTicketResponse.setMessage("Book car fail");
                    bookTicketResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                    bookTicketResponse.setVehicle(null);
                    return bookTicketResponse;
                }
            }
        } else if (insertBookingRequest.getType() == 2) {
            Optional bike = bikeDAO.findById(vehicle_id);
            if (!bike.isPresent()) {
                bookTicketResponse.setMessage("No bike available");
                bookTicketResponse.setStatus(HttpStatus.NOT_FOUND);
                return bookTicketResponse;
            } else {
                try {
                    ticketDAO.bookBike(start_date, end_date, vehicle_id, userID);
                    bookTicketResponse.setMessage("Book bike success");
                    bookTicketResponse.setStatus(HttpStatus.OK);
                    bookTicketResponse.setVehicle(bike);
                    return bookTicketResponse;
                } catch (Exception e) {
                    bookTicketResponse.setMessage("Book bike fail");
                    bookTicketResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                    bookTicketResponse.setVehicle(null);
                    return bookTicketResponse;
                }
            }
        }
        return bookTicketResponse;
    }

}
