package net.example.dao.impl;

import net.example.dao.CarSearchDAOCustom;
import net.example.entity.Car;
import net.example.model.dto.CarSearchDTO;
import net.example.model.mapper.ProductMapper;
import net.example.model.request.SearchInfRequest;
import net.example.model.response.product.CarSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;


@Repository
public class CarSearchDAOimpl implements CarSearchDAOCustom {
    @Autowired
    EntityManager entityManager;

    @Override
    public CarSearchResponse searchInfCar(SearchInfRequest searchInfRequest) {
        String sql ="SELECT * FROM car "+
                "INNER JOIN location ON car.locationid = location.id "+
                "WHERE car.possible = 1 "+
                " AND car.locationid = ? "+
                " AND car.price BETWEEN ? AND ? "+
                "AND car.end_date <= ? ";
        if (searchInfRequest.getSeat()!=0){
            sql += " AND car.seat = ? ";
        }
        if (searchInfRequest.getGear()!=0){
            sql += " AND car.gear = ? ";
        }
        if (!searchInfRequest.getCata().equals("")){
            sql += " AND car.catalog LIKE ? ";
        }
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Car.class);
            query.setParameter(1,searchInfRequest.getLocation());
            query.setParameter(2,searchInfRequest.getMoneyLow());
            query.setParameter(3,searchInfRequest.getMoneyHigh());
            query.setParameter(4,searchInfRequest.getStartDate());
            int i = 5;
            if (searchInfRequest.getSeat()!=0) {
                query.setParameter(i++, searchInfRequest.getSeat() );
            }
            if (searchInfRequest.getGear()!=0){
                query.setParameter(i++, searchInfRequest.getGear() );
            }
            if (!searchInfRequest.getCata().equals("")){
                query.setParameter(i++,"%"+ searchInfRequest.getCata() +"%");
            }
            List<Car> cars = (List<Car>) query.getResultList();
            List<CarSearchDTO> carSearchDTOS = ProductMapper.toListCarDTO(cars);
            CarSearchResponse carSearchResponse = new CarSearchResponse("search car advanced success", HttpStatus.OK,carSearchDTOS);
            return carSearchResponse;
        }catch (Exception e){
            CarSearchResponse carSearchResponse = new CarSearchResponse("search car advanced fail", HttpStatus.INTERNAL_SERVER_ERROR,null);
            return carSearchResponse;
        }
    }
}
