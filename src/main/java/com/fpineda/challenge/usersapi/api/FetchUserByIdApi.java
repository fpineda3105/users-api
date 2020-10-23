package com.fpineda.challenge.usersapi.api;

import com.fpineda.challenge.usersapi.core.model.User;
import com.fpineda.challenge.usersapi.core.port.FetchUserByIdPort;
import com.fpineda.challenge.usersapi.core.usecase.FetchUserByIdUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FetchUserByIdApi implements FetchUserByIdUseCase {

    private final FetchUserByIdPort userPort;

    @Override
    public User fetchById(long id) {        
        return userPort.fetchById(id);
    }

}
