package net.example.dao;

import net.example.entity.Bike;
import net.example.model.request.SearchInfRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeSearchDAOCustom {
    public List<Bike> searchInfBike(SearchInfRequest searchInfRequest);
    public Bike searchDetailBike(Integer id);
}
