package com.fpineda.challenge.usersapi.api;

import java.util.List;
import com.fpineda.challenge.usersapi.core.model.User;
import com.fpineda.challenge.usersapi.core.port.FetchAllUserPort;
import com.fpineda.challenge.usersapi.core.usecase.FetchAllUsersUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FetchAllUsersApi implements FetchAllUsersUseCase {

    private final FetchAllUserPort port;

    @Override
    public List<User> fetchAll() {        
        return port.fetchAll();
    }
    
}
