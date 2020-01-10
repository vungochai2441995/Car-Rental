package net.example.dao;

import net.example.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CarCatalogDAO extends JpaRepository<Car,Long> {
    @Query(nativeQuery = true, value = "SELECT DISTINCT car.catalog FROM car")
    ArrayList<String> findAllCarCatalog();
}
