package net.example.dao;

import net.example.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface TicketDAO extends JpaRepository<Ticket,Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO ticket(start_date,end_date,bike_id,user_id) VALUES (:start,:end,:vehicleID,:userID)")
    void bookBike(@Param("start")Date startDate,@Param("end")Date endDate,@Param("vehicleID") Long vehicleID,@Param("userID") Long userID);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO ticket(start_date,end_date,car_id,user_id) VALUES (:start,:end,:vehicleID,:userID)")
    void bookCar(@Param("start")Date startDate,@Param("end")Date endDate,@Param("vehicleID") Long vehicleID,@Param("userID") Long userID);


    @Query(nativeQuery = true, value = "SELECT ticket.user_id FROM ticket WHERE user_id = :userID")
    int findByUseId(@Param("userID") Long user_id);

}
