package com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository;

import java.util.List;
import java.util.stream.Collectors;
import com.fpineda.challenge.usersapi.core.command.CreateUserCommand;
import com.fpineda.challenge.usersapi.core.model.User;
import com.fpineda.challenge.usersapi.core.port.CreateUserPort;
import com.fpineda.challenge.usersapi.core.port.FetchAllUserPort;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.entity.UserEntity;

public class UserRepositoryAdapter implements CreateUserPort, FetchAllUserPort {

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

    @Override
    public List<User> fetchAll() {
        return userRepository.findAll().stream().map(mapper::toModel)
                .collect(Collectors.toList());
    }

}
