package net.example.service;

import net.example.dao.UsersDAO;
import net.example.entity.User;
import net.example.model.dto.UserDTO;
import net.example.model.mapper.UserMapper;
import net.example.model.request.CreateUsersRequest;
import net.example.model.request.LoginRequest;
import net.example.model.response.RegisterResponse;
import net.example.model.response.TokenResponse;
import net.example.util.JwtUltis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    public RegisterResponse createUser(CreateUsersRequest createUsersRequest){
        User user = usersDAO.findByUsername(createUsersRequest.getUsername());
        RegisterResponse registerResponse = new RegisterResponse();
        if (user != null){
            registerResponse.setMessage("Username is already existed");
            registerResponse.setStatus(HttpStatus.EXPECTATION_FAILED);
        }else {
            user = UserMapper.toUser(createUsersRequest);
            user.setRole("USER");
            try {
                usersDAO.save(user);
            }catch (Exception e){
                registerResponse.setMessage("Register fail");
                registerResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                return registerResponse;
            }
            registerResponse.setMessage("Register success");
            registerResponse.setStatus(HttpStatus.OK);
        }
        return registerResponse;
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

    @Override
    public TokenResponse login(LoginRequest loginReqest) {
        // Lấy thông tin user
        User user = usersDAO.findByUsername(loginReqest.getUsername());
        UserDTO userDTO = UserMapper.toUserDTO(user);
        if (user == null) {
            return new TokenResponse("Username does not exist in the system", "", HttpStatus.NOT_FOUND,null);
        }

//         Kiểm tra password
        boolean result = BCrypt.checkpw(loginReqest.getPassword(), user.getPassword());
        if (!result) {
            return new TokenResponse("Password wrong", "", HttpStatus.BAD_REQUEST,null);
        }

        String token = JwtUltis.generateToken(user);
        return new TokenResponse("Login success", token, HttpStatus.OK,userDTO);
    }
}
