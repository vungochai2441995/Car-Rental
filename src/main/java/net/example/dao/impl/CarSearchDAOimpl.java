package net.example.dao.impl;

import net.example.dao.CarSearchDAOCustom;
import net.example.entity.Car;
import net.example.model.request.SearchInfRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Repository
public class CarSearchDAOimpl implements CarSearchDAOCustom {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<Car> searchInfCar(SearchInfRequest searchInfRequest) {

        String sql = "SELECT * FROM car " +
                "INNER JOIN location ON car.locationid = location.id " +
                "WHERE car.possible = 1 " +
                " AND car.locationid = ? " +
                " AND car.price BETWEEN ? AND ? " +
                "AND car.end_date <= ? ";
        if (searchInfRequest.getSeat() != 0) {
            sql += " AND car.seat = ? ";
        }
        if (searchInfRequest.getGear() != 0) {
            sql += " AND car.gear = ? ";
        }
        if (!searchInfRequest.getCata().equals("")) {
            sql += " AND car.catalog LIKE ? ";
        }
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Car.class);
            query.setParameter(1, searchInfRequest.getLocation());
            query.setParameter(2, searchInfRequest.getMoneyLow());
            query.setParameter(3, searchInfRequest.getMoneyHigh());
            query.setParameter(4, searchInfRequest.getStartDate());
            int i = 5;
            if (searchInfRequest.getSeat() != 0) {
                query.setParameter(i++, searchInfRequest.getSeat());
            }
            if (searchInfRequest.getGear() != 0) {
                query.setParameter(i++, searchInfRequest.getGear());
            }
            if (!searchInfRequest.getCata().equals("")) {
                query.setParameter(i++, "%" + searchInfRequest.getCata() + "%");
            }
            List<Car> cars = (List<Car>) query.getResultList();
            System.out.println(cars);
            return cars;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
