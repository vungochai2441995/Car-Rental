package net.example.service;

import net.example.dao.BikeSearchDAOCustom;
import net.example.dao.CarSearchDAOCustom;
import net.example.dao.LocationDAO;
import net.example.entity.Bike;
import net.example.entity.Car;
import net.example.entity.Location;
import net.example.model.dto.BikeSearchDTO;
import net.example.model.dto.CarSearchDTO;
import net.example.model.dto.LocationDTO;
import net.example.model.request.SearchInfRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CarProductService implements ICarProductService {
    @Autowired
    LocationDAO locationDAO;

    @Autowired
    CarSearchDAOCustom carSearchDAOCustom;

    @Autowired
    BikeSearchDAOCustom bikeSearchDAOCustom;



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

    private  CarSearchDTO mapCarEntitiToModel(Car car){
        CarSearchDTO carSearchDTO = new CarSearchDTO();
        carSearchDTO.setGear(car.getGear());
        carSearchDTO.setImage(car.getUrl());
        carSearchDTO.setName(car.getName());
        carSearchDTO.setPrice(car.getPrice());
        carSearchDTO.setVehicleId(car.getLocation().getId());
        carSearchDTO.setShowroomName(car.getShowroomName());
        return carSearchDTO;
    }

    private  BikeSearchDTO mapBikeEntitiToModel(Bike bike){
        BikeSearchDTO bikeSearchDTO = new BikeSearchDTO();
        bikeSearchDTO.setGear(bike.getGear());
        bikeSearchDTO.setImage(bike.getUrl());
        bikeSearchDTO.setName(bike.getName());
        bikeSearchDTO.setPrice(bike.getPrice());
        bikeSearchDTO.setVehicleId(bike.getLocation().getId());
        bikeSearchDTO.setShowroomName(bike.getShowroom());
        return bikeSearchDTO;
    }

}
