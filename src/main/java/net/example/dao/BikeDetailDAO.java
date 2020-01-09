package net.example.dao;

import org.springframework.stereotype.Repository;

import net.example.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BikeDetailDAO extends JpaRepository<Bike,Long> {
    Bike findOneById(Long id);
}
