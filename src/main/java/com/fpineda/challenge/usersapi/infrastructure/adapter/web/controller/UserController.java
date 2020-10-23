package com.fpineda.challenge.usersapi.infrastructure.adapter.web.controller;

import java.util.List;
import com.fpineda.challenge.usersapi.core.model.User;
import com.fpineda.challenge.usersapi.core.usecase.CreateUserUseCase;
import com.fpineda.challenge.usersapi.core.usecase.FetchAllUsersUseCase;
import com.fpineda.challenge.usersapi.infrastructure.adapter.web.dto.CreateUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Api(tags = "Users Rest API")
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final FetchAllUsersUseCase fetchAllUsersUseCase;

    @ApiOperation("Create an User")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto request) {    
        return ResponseEntity.ok().body(createUserUseCase.create(request.toCommand()));
    }

    @GetMapping
    @ApiOperation("Fetch all Users")
    public ResponseEntity<List<User>> fetchAllUsers() {
        return ResponseEntity.ok().body(fetchAllUsersUseCase.fetchAll());
    }

}
