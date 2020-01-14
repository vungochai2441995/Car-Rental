package net.example.service;

import net.example.model.dto.BikeDetailDTO;
import net.example.model.dto.BikeSearchDTO;
import net.example.model.request.SearchInfRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface IBikeProductService {
    public List<BikeSearchDTO> findAllBike();
    public List<BikeSearchDTO> searchBikeInf(SearchInfRequest searchInfRequest);
    public BikeDetailDTO searchBikeDetail(Long id);
    public Set<String> findAllBikeCatalog();
}
