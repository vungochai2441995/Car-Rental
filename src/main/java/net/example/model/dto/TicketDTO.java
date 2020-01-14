package net.example.model.dto;

import lombok.Data;
import net.example.entity.Bike;
import net.example.entity.Car;

import java.util.Date;

@Data
public class TicketDTO {
    private long id;
    private Date createdDate;
    private Date endDate;
    private Date startDate;
    private Car car;
    private Bike bike;
    private UserDTO user;

}
