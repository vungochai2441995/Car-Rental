package net.example.service;

import net.example.entity.User;
import net.example.model.request.CreateUsersRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsersService {
    public List<User> getAllUsers();
    public Integer createUser(CreateUsersRequest createUsersRequest);
    public User findUserByUsername(String u);
    public User findUserByEmail(String e);
    public Integer deleteUserByUsername(String u);
    public Integer updateUser(CreateUsersRequest createUsersRequest);

}
