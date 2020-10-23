package com.fpineda.challenge.usersapi.core.port;

import com.fpineda.challenge.usersapi.core.command.CreateUserCommand;
import com.fpineda.challenge.usersapi.core.model.User;

public interface CreateUserPort {
    
    User create (CreateUserCommand command);
}
