package net.example.model.mapper;

import net.example.entity.User;
import net.example.model.request.CreateUsersRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserMapper {
    public static User toUser(CreateUsersRequest req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        String hash = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);
        return user;
    }
}
