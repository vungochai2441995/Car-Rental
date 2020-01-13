package net.example.model.mapper;

import net.example.entity.User;
import net.example.model.dto.UserDTO;
import net.example.model.request.RegisterUsersRequest;
import net.example.model.request.UpdateUserRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserMapper {
    public static User toUser(RegisterUsersRequest req) {
        User user = new User();
        user.setUsername(req.getUsername());
        String hash = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);
        user.setEmail(req.getEmail());
        user.setAddress(req.getAddress());
        user.setPhone(req.getPhone());
        user.setUrl("https://i.imgur.com/kjrD6Gb.png");
        user.setRole("USER");
        return user;
    }

    public static User toUser(User user,UpdateUserRequest req) {
        user.setEmail(req.getEmail());
        String hash = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);
        user.setPhone(req.getPhone());
        user.setAddress(req.getAddress());
        if ((req.getUrl() == null || req.getUrl().equals(""))&& !user.getUrl().equals("https://i.imgur.com/kjrD6Gb.png")){
            user.setUrl("https://i.imgur.com/kjrD6Gb.png");
        }else {
            user.setUrl(req.getUrl());
        }
        return user;
    }

    public static UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPhone(user.getPhone());
        userDTO.setEmail(user.getEmail());
        userDTO.setUrl(user.getUrl());
        return userDTO;
    }

    public static UserDTO toUserDTO(RegisterUsersRequest req) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(req.getUsername());
        userDTO.setEmail(req.getEmail());
        userDTO.setPhone(req.getPhone());
        return userDTO;
    }
}
