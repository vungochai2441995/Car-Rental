package net.example.model.mapper;

import net.example.dao.TicketDAO;
import net.example.entity.Bike;
import net.example.entity.Car;
import net.example.entity.Ticket;
import net.example.entity.User;
import net.example.model.dto.BikeSearchDTO;
import net.example.model.dto.CarSearchDTO;
import net.example.model.dto.TicketDTO;
import net.example.model.dto.UserDTO;
import net.example.model.response.product.CarSearchResponse;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

    public static List<CarSearchDTO> toListCarDTO(List<Car> cars) {
        List<CarSearchDTO> carSearchDTOS = new CopyOnWriteArrayList<>();
        for (Car car: cars) {
            CarSearchDTO carSearchDTO = new CarSearchDTO();
            carSearchDTO = toCarSearchDTO(car);
            carSearchDTOS.add(carSearchDTO);
        }
        return carSearchDTOS;
    }

    public static CarSearchDTO toCarSearchDTO(Car car) {
        CarSearchDTO carSearchDTO = new CarSearchDTO();
        carSearchDTO.setGear(car.getGear());
        carSearchDTO.setImage(car.getUrl());
        carSearchDTO.setName(car.getName());
        carSearchDTO.setPrice(car.getPrice());
        carSearchDTO.setLocation(car.getLocation().getId());
        carSearchDTO.setId(car.getId());
        carSearchDTO.setShowroomName(car.getShowroomName());
        return carSearchDTO;
    }

    public static List<BikeSearchDTO> toListBikeDTO(List<Bike> bikes) {
        List<BikeSearchDTO> bikeSearchDTOS = new CopyOnWriteArrayList<>();
        for (Bike bike: bikes) {
            BikeSearchDTO bikeSearchDTO = new BikeSearchDTO();
            bikeSearchDTO = toBikeDTO(bike);
            bikeSearchDTOS.add(bikeSearchDTO);
        }
        return bikeSearchDTOS;
    }

    private static BikeSearchDTO toBikeDTO(Bike bike){
        BikeSearchDTO bikeSearchDTO = new BikeSearchDTO();
        bikeSearchDTO.setGear(bike.getGear());
        bikeSearchDTO.setImage(bike.getUrl());
        bikeSearchDTO.setName(bike.getName());
        bikeSearchDTO.setPrice(bike.getPrice());
        bikeSearchDTO.setLocation(bike.getLocation().getId());
        bikeSearchDTO.setId(bike.getId());
        bikeSearchDTO.setShowroomName(bike.getShowroom());
        return bikeSearchDTO;
    }
}
