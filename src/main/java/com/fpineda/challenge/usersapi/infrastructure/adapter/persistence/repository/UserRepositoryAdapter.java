package com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import com.fpineda.challenge.usersapi.core.command.CreateUserCommand;
import com.fpineda.challenge.usersapi.core.command.UpdateUserCommand;
import com.fpineda.challenge.usersapi.core.model.User;
import com.fpineda.challenge.usersapi.core.port.CreateUserPort;
import com.fpineda.challenge.usersapi.core.port.DeleteUserByIdPort;
import com.fpineda.challenge.usersapi.core.port.FetchAllUserPort;
import com.fpineda.challenge.usersapi.core.port.FetchUserByIdPort;
import com.fpineda.challenge.usersapi.core.port.UpdateUserPort;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.entity.UserEntity;

public class UserRepositoryAdapter implements CreateUserPort, FetchAllUserPort, FetchUserByIdPort,
        DeleteUserByIdPort, UpdateUserPort {

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
        return userRepository.findAll().stream().map(mapper::toModel).collect(Collectors.toList());
    }

    @Override
    public User fetchById(long id) {
        var result = userRepository.findById(id);
        if (result.isPresent()) {
            return mapper.toModel(result.get());
        }
        throw new EntityNotFoundException();
    }

    @Override
    public void deleteById(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public User updateUser(final UpdateUserCommand command) {
        if (userRepository.existsById(command.getId())) {
            
            var entity = mapper.toEntity(command.toUser());
            entity.setId(command.getId());

            var entityUpdated = userRepository.save(entity);
            return mapper.toModel(entityUpdated);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

}
