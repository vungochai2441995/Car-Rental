package net.example.service;

import net.example.dao.UsersDAO;
import net.example.entity.User;
import net.example.model.request.CreateUsersRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersService implements IUsersService {
    @Autowired
    private UsersDAO usersDAO;

    public List<User> getAllUsers() {
        List<User> users = usersDAO.findAll();
        return users;
    }


    public Integer createUser(CreateUsersRequest createUsersRequest){
        User users = usersDAO.findByUsername(createUsersRequest.getUsername());
        if (users != null){
            return 0;
        }else {
            users = new User();
            users.setUsername(createUsersRequest.getUsername());
            users.setAddress(createUsersRequest.getAddress());
            users.setEmail(createUsersRequest.getEmail());
            usersDAO.save(users);
            return 1;
        }
    }

    public User findUserByUsername(String u){
        return usersDAO.findByUsername(u);
    }
    public User findUserByEmail(String e){
        return usersDAO.findByEmail(e);
    }

    public Integer deleteUserByUsername(String u){
        User users = usersDAO.findByUsername(u);
        System.out.println(users);
        if(users != null){
            usersDAO.deleteById(users.getId());
            return  1;
        }else {
            return 0;
        }
    }

    public Integer updateUser(CreateUsersRequest createUsersRequest){
        User users = usersDAO.findByUsername(createUsersRequest.getUsername());
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
