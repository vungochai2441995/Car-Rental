package net.example.service;

import net.example.model.dto.*;
import net.example.model.request.SearchInfRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICarProductService {
    public List<LocationDTO> searchLocation();
    public List<CarSearchDTO> searchCarInf(SearchInfRequest searchInfRequest);
    public List<BikeSearchDTO> searchBikeInf(SearchInfRequest searchInfRequest);
    public CarDetailDTO searchCarDetail(Long id);
    public BikeDetailDTO searchBikeDetail(Long id);
}
