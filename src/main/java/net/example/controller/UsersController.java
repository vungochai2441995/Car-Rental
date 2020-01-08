package net.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.example.entity.Users;
import net.example.model.request.CreateUsersRequest;
import net.example.model.request.SearchCarRequest;
import net.example.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
@Api(value = "User APIs")
public class UsersController {
    @Autowired
    private IUsersService usersService;

    @ApiOperation(value="Get list users", response = Users.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @GetMapping("")
    public ResponseEntity<?> getAllUsers(){
        List<Users> usersList = usersService.getAllUsers();
        return ResponseEntity.ok(usersList);
    }

    @GetMapping("/a")
    public ResponseEntity<?> tx(){
        Users usersList = usersService.test();
        return ResponseEntity.ok(usersList);
    }

    @ApiOperation(value="Create an user", response = Users.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUsersRequest createUserRequest) {
        Integer result = usersService.createUser(createUserRequest);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value="Update an user", response = Users.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody @Valid CreateUsersRequest createUserRequest) {
        int result = usersService.updateUser(createUserRequest);
        return ResponseEntity.ok("abc");
    }

    @ApiOperation(value="Find an user via username", response = Users.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @GetMapping("/username")
    public ResponseEntity<?> findUsersByUsername(@RequestParam(value = "key",required = true) String key){
        Users users = usersService.findUserByUsername(key);
        return ResponseEntity.ok(users);
    }

    @ApiOperation(value="Find an user via Email", response = Users.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @GetMapping("/email")
    public ResponseEntity<?> findUsersEmail(@RequestParam(value = "key",required = true) String key){
        Users users = usersService.findUserByEmail(key);
        return ResponseEntity.ok(users);
    }


    @ApiOperation(value="Delete an user via username", response = Users.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @DeleteMapping("")
    public ResponseEntity<?> deleteUsername(@RequestParam(value = "key",required = true) String key){
        Integer result = usersService.deleteUserByUsername(key);
        return ResponseEntity.ok(result);
    }
}
