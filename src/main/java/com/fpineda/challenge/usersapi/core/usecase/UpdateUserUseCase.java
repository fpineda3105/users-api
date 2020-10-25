package com.fpineda.challenge.usersapi.core.usecase;

import com.fpineda.challenge.usersapi.core.command.UpdateUserCommand;
import com.fpineda.challenge.usersapi.core.model.User;

public interface UpdateUserUseCase {

    User updateUser(UpdateUserCommand command);

}
