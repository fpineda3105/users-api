package com.fpineda.challenge.usersapi.api;

import com.fpineda.challenge.usersapi.core.port.DeleteUserByIdPort;
import com.fpineda.challenge.usersapi.core.usecase.DeleteUserByIdUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteUserApi implements DeleteUserByIdUseCase {

    private final DeleteUserByIdPort port;

    @Override
    public void deleteById(long id) {
        port.deleteById(id);
    }
    
}
