package net.example.service;

import net.example.dao.BikeDetailDAO;
import net.example.model.dto.*;
import net.example.model.request.InsertBookingRequest;
import net.example.model.request.SearchInfRequest;
import net.example.model.response.product.*;
import net.example.model.response.user.BookTicketResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICarProductService {
    public List<CarSearchDTO> findAllCar();
    public List<BikeSearchDTO> findAllBike();
    public BookTicketResponse insertTicket(InsertBookingRequest insertBookingRequest);
    public LocationResponse searchLocation();
    public LocationResponse searchLocationProduct();
    public CarSearchResponse searchCarInf(SearchInfRequest searchInfRequest);
    public BikeSearchResponse searchBikeInf(SearchInfRequest searchInfRequest);
    public CarDetailResponse searchCarDetail(Long id);
    public BikeDetailResponse searchBikeDetail(Long id);
    public List<BikeCatalogDTO>  findAllBikeCatalog();
    public List<CarCatalogDTO> findAllCarCatalog();
    public List<TicketDTO> findAllTicket();
}
