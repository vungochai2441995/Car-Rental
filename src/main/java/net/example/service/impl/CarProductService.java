package net.example.service.impl;

import net.example.dao.*;
import net.example.entity.*;
import net.example.model.dto.*;
import net.example.model.mapper.ProductMapper;
import net.example.model.request.InsertBookingRequest;
import net.example.model.request.SearchInfRequest;
import net.example.model.response.BookTicketResponse;
import net.example.service.ICarProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class CarProductService implements ICarProductService {
    @Autowired
    CarDAO carDAO;

    @Autowired
    CarSearchDAOCustom carSearchDAOCustom;

    @Autowired
    CarDetailDAO carDetailDAO;

    @Override
    public List<CarSearchDTO> searchCarInf(SearchInfRequest searchInfRequest) {
        List<CarSearchDTO> carSearchDTOS = new CopyOnWriteArrayList<>();
        List<Car> cars = carSearchDAOCustom.searchInfCar(searchInfRequest);
        for (Car car : cars) {
            CarSearchDTO carSearchDTO = new CarSearchDTO();
            carSearchDTO = mapCarEntityToModel(car);
            carSearchDTOS.add(carSearchDTO);
        }
        return carSearchDTOS;
    }

    @Override
    public CarDetailDTO searchCarDetail(Long id) {
        Car car = carDetailDAO.findOneById(id);
        CarDetailDTO carDetailDTO = new CarDetailDTO();
        BeanUtils.copyProperties(car, carDetailDTO);
        return carDetailDTO;
    }

    @Override
    public Set<String> findAllCarCatalog() {
        List<Car> cars = carDAO.findAll();
        Set<String> catalog = new CopyOnWriteArraySet<>();
        for (Car car : cars) {
            catalog.add(car.getCata());
        }
        return catalog;
    }



    @Override
    public List<CarSearchDTO> findAllCar() {
        List<Car> cars = carDAO.findAll();
        List<CarSearchDTO> carSearchDTOS = new CopyOnWriteArrayList<>();
        for (Car car : cars) {
            CarSearchDTO carSearchDTO = new CarSearchDTO();
            carSearchDTO = mapCarEntityToModel(car);
            carSearchDTOS.add(carSearchDTO);
        }
        return carSearchDTOS;
    }

    private CarSearchDTO mapCarEntityToModel(Car car) {
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

    private BikeSearchDTO mapBikeEntityToModel(Bike bike) {
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
