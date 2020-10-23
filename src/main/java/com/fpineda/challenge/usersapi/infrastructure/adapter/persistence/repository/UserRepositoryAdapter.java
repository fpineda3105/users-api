package com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository;

import com.fpineda.challenge.usersapi.core.command.CreateUserCommand;
import com.fpineda.challenge.usersapi.core.model.User;
import com.fpineda.challenge.usersapi.core.port.CreateUserPort;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.entity.UserEntity;

public class UserRepositoryAdapter implements CreateUserPort {

    private final JpaUserRepository userRepository;

    private final EntityMapper<User, UserEntity> mapper;

    public UserRepositoryAdapter(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new UserEntityMapper();
    }

    @Override
    public User create(final CreateUserCommand command) {        

        var userEntity = mapper.toEntity(command.toUser());
        
        var userEntityResult = userRepository.save(userEntity);
        return mapper.toModel(userEntityResult);
        
    }

}
