package net.example.dao;

import net.example.entity.Car;
import net.example.model.request.SearchInfRequest;
import net.example.model.response.product.CarSearchResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarSearchDAOCustom{
    public CarSearchResponse searchInfCar(SearchInfRequest searchInfRequest);
}
