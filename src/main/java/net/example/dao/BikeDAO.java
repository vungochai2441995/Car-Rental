package net.example.dao;

import net.example.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeDAO extends JpaRepository<Bike,Long> {
}
