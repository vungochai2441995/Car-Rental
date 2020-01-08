package net.example.service;

import net.example.entity.Users;
import net.example.model.request.CreateUsersRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsersService {
    public List<Users> getAllUsers();
    public Integer createUser(CreateUsersRequest createUsersRequest);
    public Users findUserByUsername(String u);
    public Users findUserByEmail(String e);
    public Integer deleteUserByUsername(String u);
    public Integer updateUser(CreateUsersRequest createUsersRequest);
    public Users test();

}
