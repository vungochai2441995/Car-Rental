package net.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.example.entity.User;
import net.example.model.request.RegisterUsersRequest;
import net.example.model.request.LoginRequest;
import net.example.model.request.UpdateUserRequest;
import net.example.model.response.CommonUserResponse;
import net.example.model.response.TokenResponse;
import net.example.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private boolean loginStatus = false;

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
            loginStatus = false;
            return ResponseEntity.status(result.getStatusCode()).body(result);
        }
        loginStatus = true;
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value="Đăng ký", response = CommonUserResponse.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody @Valid RegisterUsersRequest createUserRequest) {
        CommonUserResponse result = new CommonUserResponse();
        result = usersService.createUser(createUserRequest);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value="Cập nhật thông tin người dùng", response = CommonUserResponse.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        CommonUserResponse result = new CommonUserResponse();
        result = usersService.updateUser(updateUserRequest);
        return ResponseEntity.ok(result);
    }
}
