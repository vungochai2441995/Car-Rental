package net.example.dao;

import net.example.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BikeCatalogDAO extends JpaRepository<Bike,Long> {
    @Query(nativeQuery = true, value = "SELECT DISTINCT bike.catalog FROM bike")
    ArrayList<String> findAllBikeCatalog();
}
