package com.fpineda.challenge.usersapi.infrastructure.adapter.web.controller;

import com.fpineda.challenge.usersapi.core.model.User;
import com.fpineda.challenge.usersapi.core.usecase.CreateUserUseCase;
import com.fpineda.challenge.usersapi.infrastructure.adapter.web.dto.CreateUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final CreateUserUseCase userUseCase;

    @PostMapping
    public ResponseEntity<User> name(@RequestBody CreateUserDto request) {    
        return ResponseEntity.ok().body(userUseCase.create(request.toCommand()));

    }

}
