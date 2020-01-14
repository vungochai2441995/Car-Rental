package net.example.dao;

import net.example.entity.Car;
import net.example.model.request.SearchInfRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarSearchDAOCustom {
    public List<Car> searchInfCar(SearchInfRequest searchInfRequest);
}
