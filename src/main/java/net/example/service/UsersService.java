package net.example.service;

import net.example.dao.UsersDAO;
import net.example.entity.User;
import net.example.model.dto.UserDTO;
import net.example.model.mapper.UserMapper;
import net.example.model.request.RegisterUsersRequest;
import net.example.model.request.LoginRequest;
import net.example.model.request.UpdateUserRequest;
import net.example.model.response.user.CommonUserResponse;
import net.example.model.response.user.TokenResponse;
import net.example.util.JwtUltis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
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


    public CommonUserResponse createUser(RegisterUsersRequest registerUsersRequest) {
        User user = usersDAO.findByUsername(registerUsersRequest.getUsername());
        CommonUserResponse commonUserResponse = new CommonUserResponse();
        if (user != null) {
            commonUserResponse.setMessage("Username is already existed");
            commonUserResponse.setStatus(HttpStatus.BAD_REQUEST);
            commonUserResponse.setUserDTO(null);
        } else {
            user = UserMapper.toUser(registerUsersRequest);
            try {
                usersDAO.save(user);
            } catch (Exception e) {
                commonUserResponse.setMessage("Register fail");
                commonUserResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                return commonUserResponse;
            }
            commonUserResponse.setMessage("Register success");
            commonUserResponse.setStatus(HttpStatus.OK);
            commonUserResponse.setUserDTO(UserMapper.toUserDTO(user));
        }
        return commonUserResponse;
    }

    public User findUserByUsername(String u) {
        return usersDAO.findByUsername(u);
    }

    public User findUserByEmail(String e) {
        return usersDAO.findByEmail(e);
    }

    public CommonUserResponse deleteUserByUsername(String u) {
        User users = usersDAO.findByUsername(u);
        if (users != null) {
            try {
                usersDAO.deleteById(users.getId());
                CommonUserResponse commonUserResponse = new CommonUserResponse("Delete user success",HttpStatus.OK,null);
                return commonUserResponse;
            } catch (Exception e) {
                CommonUserResponse commonUserResponse = new CommonUserResponse("Delete user fail",HttpStatus.INTERNAL_SERVER_ERROR,null);
                return commonUserResponse;
            }
        } else {
            CommonUserResponse commonUserResponse = new CommonUserResponse("User is not exist",HttpStatus.BAD_REQUEST,null);
            return commonUserResponse;
        }
    }

    public CommonUserResponse updateUser(UpdateUserRequest updateUserRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        User user = usersDAO.findByUsername(username);
        user = UserMapper.toUser(user,updateUserRequest);
        try {
            usersDAO.save(user);
            UserDTO userDTO = UserMapper.toUserDTO(user);
            CommonUserResponse commonUserResponse = new CommonUserResponse("Update user success",HttpStatus.OK,userDTO);
            return commonUserResponse;
        }catch (Exception e) {
            CommonUserResponse commonUserResponse = new CommonUserResponse("Update user fail",HttpStatus.INTERNAL_SERVER_ERROR,null);
            return commonUserResponse;
        }
}

    @Override
    public TokenResponse login(LoginRequest loginReqest) {
        // Lấy thông tin user
        User user = usersDAO.findByUsername(loginReqest.getUsername());
        if (user == null) {
            return new TokenResponse("Username does not exist in the system", "", HttpStatus.NOT_FOUND, null);
        }

        UserDTO userDTO = UserMapper.toUserDTO(user);
//         Kiểm tra password
        boolean result = BCrypt.checkpw(loginReqest.getPassword(), user.getPassword());
        if (!result) {
            return new TokenResponse("Password wrong", "", HttpStatus.BAD_REQUEST, null);
        }

        String token = JwtUltis.generateToken(user);
        return new TokenResponse("Login success", token, HttpStatus.OK, userDTO);
        }
    }
