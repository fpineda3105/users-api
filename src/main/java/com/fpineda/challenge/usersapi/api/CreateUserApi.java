package com.fpineda.challenge.usersapi.api;

import com.fpineda.challenge.usersapi.core.command.CreateUserCommand;
import com.fpineda.challenge.usersapi.core.model.User;
import com.fpineda.challenge.usersapi.core.port.CreateUserPort;
import com.fpineda.challenge.usersapi.core.usecase.CreateUserUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateUserApi implements CreateUserUseCase {

    private final CreateUserPort userPort;

    @Override
    public User create(final CreateUserCommand command) {        
        return userPort.create(command);
    }
    
}
