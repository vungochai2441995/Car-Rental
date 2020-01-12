package net.example.service;

import net.example.entity.Ticket;
import net.example.model.dto.*;
import net.example.model.request.InsertBookingRequest;
import net.example.model.request.SearchInfRequest;
import net.example.model.response.BookTicketResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICarProductService {
    public List<CarSearchDTO> findAllCar();
    public List<BikeSearchDTO> findAllBike();
    public BookTicketResponse insertTicket(InsertBookingRequest insertBookingRequest);
    public List<LocationDTO> searchLocation();
    public List<CarSearchDTO> searchCarInf(SearchInfRequest searchInfRequest);
    public List<BikeSearchDTO> searchBikeInf(SearchInfRequest searchInfRequest);
    public CarDetailDTO searchCarDetail(Long id);
    public BikeDetailDTO searchBikeDetail(Long id);
    public List<BikeCatalogDTO>  findAllBikeCatalog();
    public List<CarCatalogDTO> findAllCarCatalog();
    public List<TicketDTO> findAllTicket();
}
