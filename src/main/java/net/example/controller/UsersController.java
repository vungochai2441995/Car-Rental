package net.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.example.entity.User;
import net.example.model.request.CreateUsersRequest;
import net.example.model.request.LoginRequest;
import net.example.model.response.RegisterResponse;
import net.example.model.response.TokenResponse;
import net.example.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 2592000)
@RequestMapping("/users")
@Api(value = "User APIs")
public class UsersController {

    @Autowired
    private IUsersService usersService;

    @ApiOperation(value="Tìm tất cả thông tin người dùng", response = User.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @GetMapping("/list")
    public ResponseEntity<?> getAllUsers(){
        List<User> usersList = usersService.getAllUsers();
        return ResponseEntity.ok(usersList);
    }

    @ApiOperation(value="Đăng nhập", response = TokenResponse.class)
    @ApiResponses({
            @ApiResponse(code = 404, message="Email does not exist in the system"),
            @ApiResponse(code = 400, message="Password wrong"),
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest req) {
        TokenResponse result = usersService.login(req);
        if (!result.getStatusCode().equals(HttpStatus.OK)) {
            return ResponseEntity.status(result.getStatusCode()).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value="Đăng ký", response = RegisterResponse.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @PutMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUsersRequest createUserRequest) {
        RegisterResponse result = usersService.createUser(createUserRequest);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value="cập nhật thông tin người dùng", response = User.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody @Valid CreateUsersRequest createUserRequest) {
        int result = usersService.updateUser(createUserRequest);
        return ResponseEntity.ok(result);
    }
}
