package net.example.dao;

import net.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDAO extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

//    @Query("SELECT u.address FROM Users u WHERE u.id = 18")
//    User test();
}
