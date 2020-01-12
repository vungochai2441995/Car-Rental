package net.example.model.dto;

import lombok.Data;
import net.example.entity.Bike;
import net.example.entity.Car;
import net.example.entity.User;

import javax.persistence.*;
import java.util.Date;

@Data
public class TicketDTO {
    private long id;
    private Date createdDate;
    private Date endDate;
    private Date startDate;
    private Long userID;
    private Car car;
    private Bike bike;

}
