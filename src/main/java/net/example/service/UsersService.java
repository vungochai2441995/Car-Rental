package net.example.service;

import io.swagger.models.auth.In;
import net.example.dao.UsersDAO;
import net.example.entity.Users;
import net.example.model.request.CreateUsersRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class UsersService implements IUsersService {
    @Autowired
    private UsersDAO usersDAO;

    public List<Users> getAllUsers() {
        List<Users> users = usersDAO.findAll();
        return null;
    }

    public Users test(){
        Users users = usersDAO.test();
        return users;
    }

    public Integer createUser(CreateUsersRequest createUsersRequest){
        Users users = usersDAO.findByUsername(createUsersRequest.getUsername());
        if (users != null){
            return 0;
        }else {
            users = new Users();
            users.setUsername(createUsersRequest.getUsername());
            users.setAddress(createUsersRequest.getAddress());
            users.setEmail(createUsersRequest.getEmail());
            usersDAO.save(users);
            return 1;
        }
    }

    public Users findUserByUsername(String u){
        return usersDAO.findByUsername(u);
    }
    public Users findUserByEmail(String e){
        return usersDAO.findByEmail(e);
    }

    public Integer deleteUserByUsername(String u){
        Users users = usersDAO.findByUsername(u);
        System.out.println(users);
        if(users != null){
            usersDAO.deleteById(users.getId());
            return  1;
        }else {
            return 0;
        }
    }

    public Integer updateUser(CreateUsersRequest createUsersRequest){
        Users users = usersDAO.findByUsername(createUsersRequest.getUsername());
        if (users == null){
            return 0;
        }else {
            users.setAddress(createUsersRequest.getAddress());
            users.setEmail(createUsersRequest.getEmail());
            usersDAO.save(users);
            return 1;
        }
    }
}
