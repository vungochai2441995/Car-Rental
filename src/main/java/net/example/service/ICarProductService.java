package net.example.service;

import net.example.model.dto.*;
import net.example.model.request.InsertBookingRequest;
import net.example.model.request.SearchInfRequest;
import net.example.model.response.BookTicketResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ICarProductService {
    public List<CarSearchDTO> findAllCar();

    public List<CarSearchDTO> searchCarInf(SearchInfRequest searchInfRequest);

    public CarDetailDTO searchCarDetail(Long id);

    public Set<String> findAllCarCatalog();


}
