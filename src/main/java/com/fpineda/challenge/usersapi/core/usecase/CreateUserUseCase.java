package com.fpineda.challenge.usersapi.core.usecase;

import com.fpineda.challenge.usersapi.core.command.CreateUserCommand;
import com.fpineda.challenge.usersapi.core.model.User;

public interface CreateUserUseCase {
    
    User create(CreateUserCommand command);
}
