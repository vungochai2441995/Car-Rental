package net.example.service;

import net.example.dao.*;
import net.example.entity.*;
import net.example.model.dto.*;
import net.example.model.mapper.ProductMapper;
import net.example.model.request.InsertBookingRequest;
import net.example.model.request.SearchInfRequest;
import net.example.model.response.product.*;
import net.example.model.response.user.BookTicketResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CarProductService implements ICarProductService {
    @Autowired
    CarDAO carDAO;

    @Autowired
    BikeDAO bikeDAO;

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

    @Autowired
    BikeCatalogDAO bikeCatalogDAO;

    @Autowired
    CarCatalogDAO carCatalogDAO;

    @Autowired
    UsersDAO usersDAO;


    @Override
    public LocationResponse searchLocation() {
        try {
            List<Location> locations = locationDAO.findAll();
            List<LocationDTO> locationDTOS = new CopyOnWriteArrayList<>();
            for (Location location:locations) {
                LocationDTO locationDTO = new LocationDTO();
                BeanUtils.copyProperties(location,locationDTO);
                locationDTOS.add(locationDTO);
            }
            LocationResponse locationResponse = new LocationResponse("Find all locations success",HttpStatus.OK,locationDTOS);
            return locationResponse;
        }catch (Exception e) {
            LocationResponse locationResponse = new LocationResponse("Can not find location",HttpStatus.INTERNAL_SERVER_ERROR,null);
            return locationResponse;
        }

    }

    @Override
    public LocationResponse searchLocationProduct() {
        try {
            List<Location> locations = locationDAO.findAllLocationInProduction();
            List<LocationDTO> locationDTOS = new CopyOnWriteArrayList<>();
            for (Location location:locations) {
                LocationDTO locationDTO = new LocationDTO();
                BeanUtils.copyProperties(location,locationDTO);
                locationDTOS.add(locationDTO);
            }
            LocationResponse locationResponse = new LocationResponse("Find all locations success",HttpStatus.OK,locationDTOS);
            return locationResponse;
        }catch (Exception e) {
            LocationResponse locationResponse = new LocationResponse("Can not find location",HttpStatus.INTERNAL_SERVER_ERROR,null);
            return locationResponse;
        }
    }


    @Override
    public CarSearchResponse searchCarInf(SearchInfRequest searchInfRequest) {
        CarSearchResponse carSearchResponse = carSearchDAOCustom.searchInfCar(searchInfRequest);
        return carSearchResponse;
    }

    @Override
    public BikeSearchResponse searchBikeInf(SearchInfRequest searchInfRequest) {
        BikeSearchResponse bikeSearchResponse = bikeSearchDAOCustom.searchInfBike(searchInfRequest);
        return bikeSearchResponse;
    }

    @Override
    public CarDetailResponse searchCarDetail(Long id) {
        try {
            Car car = carDetailDAO.findOneById(id);
            CarDetailDTO carDetailDTO = new CarDetailDTO();
            BeanUtils.copyProperties(car,carDetailDTO);
            CarDetailResponse carDetailResponse = new CarDetailResponse("search detail car success",HttpStatus.OK,carDetailDTO);
            return carDetailResponse;
        }catch (Exception e) {
            CarDetailResponse carDetailResponse = new CarDetailResponse("search detail car fail",HttpStatus.INTERNAL_SERVER_ERROR,null);
            return carDetailResponse;
        }
    }

    @Override
    public BikeDetailResponse searchBikeDetail(Long id) {
        try {
            Bike bike = bikeDetailDAO.findOneById(id);
            BikeDetailDTO bikeDetailDTO = new BikeDetailDTO();
            BeanUtils.copyProperties(bike,bikeDetailDTO);
            BikeDetailResponse bikeDetailResponse = new BikeDetailResponse("search detail bike success",HttpStatus.OK,bikeDetailDTO);
            return bikeDetailResponse;
        }catch (Exception e) {
            BikeDetailResponse bikeDetailResponse = new BikeDetailResponse("search detail bike success",HttpStatus.OK,null);
            return bikeDetailResponse;
        }
    }

    @Override
    public List<BikeCatalogDTO> findAllBikeCatalog() {
        List<BikeCatalogDTO> bikeCatalogDTOs = new CopyOnWriteArrayList<>();
        ArrayList<String> results = bikeCatalogDAO.findAllBikeCatalog();
        for (String result:results) {
            BikeCatalogDTO bikeCatalogDTO = new BikeCatalogDTO();
            bikeCatalogDTO.setName(result);
            bikeCatalogDTOs.add(bikeCatalogDTO);
        }
        return bikeCatalogDTOs;
    }

    @Override
    public List<CarCatalogDTO> findAllCarCatalog() {
        List<CarCatalogDTO> carCatalogDTOS = new CopyOnWriteArrayList<>();
        ArrayList<String> results = carCatalogDAO.findAllCarCatalog();
        for (String result:results) {
            CarCatalogDTO carCatalogDTO = new CarCatalogDTO();
            carCatalogDTO.setName(result);
            carCatalogDTOS.add(carCatalogDTO);
        }
        return carCatalogDTOS;
    }

    @Override
    public List<TicketDTO> findAllTicket() {
        List<Ticket> tickets = ticketDAO.findAll();
        List<TicketDTO> ticketDTOS = new CopyOnWriteArrayList<>();
        for (Ticket ticket: tickets) {
            TicketDTO ticketDTO = ProductMapper.toTicketDTO(ticket);
            ticketDTOS.add(ticketDTO);
        }
        return ticketDTOS;
    }

    @Override
    public List<CarSearchDTO> findAllCar() {
        List<Car> cars = carDAO.findAll();
        List<CarSearchDTO> carSearchDTOS = ProductMapper.toListCarDTO(cars);
        return carSearchDTOS;
    }

    @Override
    public List<BikeSearchDTO> findAllBike() {
        List<Bike> bikes = bikeDAO.findAll();
        List<BikeSearchDTO> bikeSearchDTOS = ProductMapper.toListBikeDTO(bikes);
        return bikeSearchDTOS;
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
        }catch (Exception e){
            bookTicketResponse.setMessage("Can not find user");
            bookTicketResponse.setStatus(HttpStatus.NOT_FOUND);
            return bookTicketResponse;
        }
        Long userID = user.getId();
        List<Ticket> tickets = ticketDAO.findAllByUser_id(userID);
        System.out.println(tickets);
        if (insertBookingRequest.getType() == 1) {
            Optional car = carDAO.findById(vehicle_id);
            if(!car.isPresent()){
                bookTicketResponse.setMessage("No Car available");
                bookTicketResponse.setStatus(HttpStatus.NOT_FOUND);
                return bookTicketResponse;
            }else{
                ticketDAO.bookCar(start_date, end_date, vehicle_id,userID);
            }
        } else if (insertBookingRequest.getType() == 2) {
            Optional bike = bikeDAO.findById(vehicle_id);
            if(!bike.isPresent()){
                bookTicketResponse.setMessage("No Bike available");
                bookTicketResponse.setStatus(HttpStatus.NOT_FOUND);
                return bookTicketResponse;
            }
            ticketDAO.bookBike(start_date, end_date, vehicle_id,userID);
        }

        if (ticketDAO.findByUseId(userID) == userID){
            bookTicketResponse.setMessage("book vehicle success");
            bookTicketResponse.setStatus(HttpStatus.OK);
        }else {
            bookTicketResponse.setMessage("Internal Server Error");
            bookTicketResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return bookTicketResponse;
    }

}
