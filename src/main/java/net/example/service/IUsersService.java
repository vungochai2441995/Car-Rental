package net.example.service;

import net.example.entity.User;
import net.example.model.request.CreateUsersRequest;
import net.example.model.request.LoginRequest;
import net.example.model.response.RegisterResponse;
import net.example.model.response.TokenResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsersService {
    public List<User> getAllUsers();
    public RegisterResponse createUser(CreateUsersRequest createUsersRequest);
    public User findUserByUsername(String u);
    public User findUserByEmail(String e);
    public Integer deleteUserByUsername(String u);
    public Integer updateUser(CreateUsersRequest createUsersRequest);
    public TokenResponse login(LoginRequest req);

}
