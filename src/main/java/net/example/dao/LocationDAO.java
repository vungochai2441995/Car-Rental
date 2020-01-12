package net.example.dao;

import net.example.entity.Location;
import net.example.model.dto.LocationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface LocationDAO extends JpaRepository<Location,Long>{
    @Query(nativeQuery = true, value =
            "Select distinct t1.id,t1.name from location t1, car t2, bike t3\n" +
                    "where t1.id = t2.locationid or t1.id = t3.locationid")
    List<Location> findAllLocationInProduction();
}
