package net.example.dao;

import net.example.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDetailDAO extends JpaRepository<Car,Long> {
    Car findOneById(Long id);
}
