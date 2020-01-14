package net.example.dao;

import net.example.entity.Bike;
import net.example.model.request.SearchInfRequest;
import net.example.model.response.product.BikeSearchResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeSearchDAOCustom {
    public BikeSearchResponse searchInfBike(SearchInfRequest searchInfRequest);
    public Bike searchDetailBike(Integer id);
}
