package com.fpineda.challenge.usersapi.core.port;

import com.fpineda.challenge.usersapi.core.command.UpdateUserCommand;
import com.fpineda.challenge.usersapi.core.model.User;

public interface UpdateUserPort {
    
    User updateUser(UpdateUserCommand command);
}
