package com.fpineda.challenge.usersapi.api;

import com.fpineda.challenge.usersapi.core.command.UpdateUserCommand;
import com.fpineda.challenge.usersapi.core.model.User;
import com.fpineda.challenge.usersapi.core.port.UpdateUserPort;
import com.fpineda.challenge.usersapi.core.usecase.UpdateUserUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateUserApi implements UpdateUserUseCase {

    private final UpdateUserPort port;

    @Override
    public User updateUser(final UpdateUserCommand command) {
        return port.updateUser(command);
    }
    
}
