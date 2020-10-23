package com.fpineda.challenge.usersapi.core.usecase;

import com.fpineda.challenge.usersapi.core.model.User;

public interface FetchUserByIdUseCase {

    User fetchById(long id);
    
}
