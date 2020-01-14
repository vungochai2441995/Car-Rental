package net.example.service;

import net.example.entity.Ticket;
import net.example.entity.User;
import net.example.model.dto.*;
import net.example.model.request.InsertBookingRequest;
import net.example.model.request.SearchInfRequest;
import net.example.model.response.BookTicketResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public interface ICarProductService {
    public List<CarSearchDTO> findAllCar();
    public List<BikeSearchDTO> findAllBike();
    public BookTicketResponse insertTicket(InsertBookingRequest insertBookingRequest);
    public List<LocationDTO> searchLocation();
    public List<LocationDTO> searchLocationProduct();
    public List<CarSearchDTO> searchCarInf(SearchInfRequest searchInfRequest);
    public List<BikeSearchDTO> searchBikeInf(SearchInfRequest searchInfRequest);
    public CarDetailDTO searchCarDetail(Long id);
    public BikeDetailDTO searchBikeDetail(Long id);
    public Set<String> findAllBikeCatalog();
    public Set<String> findAllCarCatalog();
    public List<TicketDTO> findAllTicket();
}
