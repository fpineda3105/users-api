package com.fpineda.challenge.usersapi.core.port;

import com.fpineda.challenge.usersapi.core.model.User;

public interface FetchUserByIdPort {

    User fetchById(long id);
    
}
