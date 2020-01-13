package net.example.service;

import net.example.entity.User;
import net.example.model.request.RegisterUsersRequest;
import net.example.model.request.LoginRequest;
import net.example.model.request.UpdateUserRequest;
import net.example.model.response.CommonUserResponse;
import net.example.model.response.TokenResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsersService {
    public List<User> getAllUsers();
    public CommonUserResponse createUser(RegisterUsersRequest registerUsersRequest);
    public CommonUserResponse updateUser(UpdateUserRequest updateUserRequest);
    public User findUserByUsername(String u);
    public User findUserByEmail(String e);
    public CommonUserResponse deleteUserByUsername(String u);
    public TokenResponse login(LoginRequest req);

}
