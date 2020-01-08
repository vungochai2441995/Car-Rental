package net.example.dao;

import net.example.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersDAO extends JpaRepository<Users,Long> {
    Users findByUsername(String username);
    Users findByEmail(String email);

    @Query("SELECT u.address FROM Users u WHERE u.id = 18")
    Users test();
}
