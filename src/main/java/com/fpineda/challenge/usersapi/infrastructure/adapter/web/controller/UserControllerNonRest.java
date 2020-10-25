package com.fpineda.challenge.usersapi.infrastructure.adapter.web.controller;

import java.net.URI;
import java.util.List;
import com.fpineda.challenge.usersapi.core.model.User;
import com.fpineda.challenge.usersapi.core.usecase.CreateUserUseCase;
import com.fpineda.challenge.usersapi.core.usecase.DeleteUserByIdUseCase;
import com.fpineda.challenge.usersapi.core.usecase.FetchAllUsersUseCase;
import com.fpineda.challenge.usersapi.core.usecase.FetchUserByIdUseCase;
import com.fpineda.challenge.usersapi.core.usecase.UpdateUserUseCase;
import com.fpineda.challenge.usersapi.infrastructure.adapter.web.dto.CreateUserDto;
import com.fpineda.challenge.usersapi.infrastructure.adapter.web.dto.UpdateUserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@Api(tags = "Users Non Rest API")
@RestController
@AllArgsConstructor
public class UserControllerNonRest {

    private final CreateUserUseCase createUserUseCase;
    private final FetchAllUsersUseCase fetchAllUsersUseCase;
    private final FetchUserByIdUseCase fetchUserUseCase;
    private final DeleteUserByIdUseCase deleteUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final UserController restController;

    @ApiOperation(value = "Create an User", code = 201)
    @PostMapping("/createUsers")
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto request) {
        var result = createUserUseCase.create(request.toCommand());
        URI location =
                fromCurrentContextPath().path("users/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).body(result);
    }

    @GetMapping("/getusers")
    @ApiOperation(value = "Get all Users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> fetchAllUsers() {
        return ResponseEntity.ok().body(fetchAllUsersUseCase.fetchAll());
    }

    @GetMapping("/getusersById/{id}")
    @ApiOperation(value = "Get User by Id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> fetchUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(fetchUserUseCase.fetchById(id));
    }

    @DeleteMapping("/deleteUsersById/{id}")
    @ApiOperation("Delete User by Id")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") long id) {
        deleteUserUseCase.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/updateUsersById/{id}")
    @ApiOperation(value = "Update User by Id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id,
            @RequestBody UpdateUserDto updateRequest) {
        return restController.updateUser(id, updateRequest);
    }
    
}
