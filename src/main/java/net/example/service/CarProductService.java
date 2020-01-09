package net.example.service;

import net.example.dao.*;
import net.example.entity.Bike;
import net.example.entity.Car;
import net.example.entity.Location;
import net.example.entity.Ticket;
import net.example.model.dto.*;
import net.example.model.request.InsertBookingRequest;
import net.example.model.request.SearchInfRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CarProductService implements ICarProductService {
    @Autowired
    LocationDAO locationDAO;

    @Autowired
    CarSearchDAOCustom carSearchDAOCustom;

    @Autowired
    BikeSearchDAOCustom bikeSearchDAOCustom;

    @Autowired
    BikeDetailDAO bikeDetailDAO;

    @Autowired
    CarDetailDAO carDetailDAO;

    @Autowired
    TicketDAO ticketDAO;

    @Override
    public List<LocationDTO> searchLocation() {

        List<Location> locations = locationDAO.findAll();
        System.out.println(locations);
        List<LocationDTO> locationDTOS = new CopyOnWriteArrayList<>();
        for (Location location:locations) {
            LocationDTO locationDTO = new LocationDTO();
            BeanUtils.copyProperties(location,locationDTO);
            locationDTOS.add(locationDTO);
        }
        return locationDTOS;
    }


    @Override
    public List<CarSearchDTO> searchCarInf(SearchInfRequest searchInfRequest) {
        List<CarSearchDTO> carSearchDTOS = new CopyOnWriteArrayList<>();
        List<Car> cars =  carSearchDAOCustom.searchInfCar(searchInfRequest);
        for (Car car: cars) {
            CarSearchDTO carSearchDTO = new CarSearchDTO();
            carSearchDTO = mapCarEntitiToModel(car);
            carSearchDTOS.add(carSearchDTO);
        }
        return carSearchDTOS;
    }

    @Override
    public List<BikeSearchDTO> searchBikeInf(SearchInfRequest searchInfRequest) {
        List<BikeSearchDTO> bikeSearchDTOS = new CopyOnWriteArrayList<>();
        List<Bike> bikes =  bikeSearchDAOCustom.searchInfBike(searchInfRequest);
        for (Bike bike: bikes) {
            BikeSearchDTO bikeSearchDTO = new BikeSearchDTO();
            bikeSearchDTO = mapBikeEntitiToModel(bike);
            bikeSearchDTOS.add(bikeSearchDTO);
        }
        return bikeSearchDTOS;
    }

    @Override
    public CarDetailDTO searchCarDetail(Long id) {
        Car car = carDetailDAO.findOneById(id);
        CarDetailDTO carDetailDTO = new CarDetailDTO();
        BeanUtils.copyProperties(car,carDetailDTO);
        return carDetailDTO;
    }

    @Override
    public BikeDetailDTO searchBikeDetail(Long id) {
        Bike bike = bikeDetailDAO.findOneById(id);
        BikeDetailDTO bikeDetailDTO = new BikeDetailDTO();
        BeanUtils.copyProperties(bike,bikeDetailDTO);
        return bikeDetailDTO;
    }

    @Override
    public Long insertTicket(InsertBookingRequest insertBookingRequest) {
        Date start_date = insertBookingRequest.getStartDate();
        Date end_date = insertBookingRequest.getEndDate();
        Long vehicle_id = insertBookingRequest.getVehicle_id();
        Long user_id = insertBookingRequest.getUser_id();
        if (insertBookingRequest.getType() == 1) {
            try {
                ticketDAO.bookCar(start_date, end_date, vehicle_id, user_id);
            } catch (DataAccessException e) {
                return null;
            }
            if (ticketDAO.findByUseId(user_id) == user_id){
                return user_id;
            } else return null;
        } else if (insertBookingRequest.getType() == 2) {

            ticketDAO.bookBike(start_date, end_date, vehicle_id, user_id);
            if (ticketDAO.findByUseId(user_id) == user_id) {
                return user_id;
            }
        }
        return null;
    }

    private  CarSearchDTO mapCarEntitiToModel(Car car){
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

    private  BikeSearchDTO mapBikeEntitiToModel(Bike bike){
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
