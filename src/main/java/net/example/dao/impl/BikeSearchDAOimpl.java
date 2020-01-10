package net.example.dao.impl;

import net.example.dao.BikeSearchDAOCustom;
import net.example.entity.Bike;
import net.example.entity.Car;
import net.example.model.request.SearchInfRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BikeSearchDAOimpl implements BikeSearchDAOCustom {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<Bike> searchInfBike(SearchInfRequest searchInfRequest) {

        String sql ="SELECT * FROM bike "+
                "INNER JOIN location ON bike.locationid = location.id "+
                "WHERE bike.possible = 1 "+
                " AND bike.locationid = ? "+
                " AND bike.price BETWEEN ? AND ? "+
                "AND bike.end_date <= ? ";
        if (searchInfRequest.getGear()!=0){
            sql += " AND bike.gear = ? ";
        }
        if (!searchInfRequest.getCata().equals("")){
            sql += " AND bike.catalog LIKE ? ";
        }
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Bike.class);
            query.setParameter(1,searchInfRequest.getLocation());
            query.setParameter(2,searchInfRequest.getMoneyLow());
            query.setParameter(3,searchInfRequest.getMoneyHigh());
            query.setParameter(4,searchInfRequest.getStartDate());
            int i = 5;
            if (searchInfRequest.getGear()!=0){
                query.setParameter(i++, searchInfRequest.getGear() );
            }
            if (!searchInfRequest.getCata().equals("")){
                query.setParameter(i++,"%"+ searchInfRequest.getCata() +"%");
            }
            List<Bike> bikes = (List<Bike>) query.getResultList();
            return bikes;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Bike searchDetailBike(Integer id) {
        return null;
    }
}
