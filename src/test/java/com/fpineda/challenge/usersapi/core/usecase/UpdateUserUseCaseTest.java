package com.fpineda.challenge.usersapi.core.usecase;

import javax.persistence.EntityNotFoundException;
import com.fpineda.challenge.usersapi.api.UpdateUserApi;
import com.fpineda.challenge.usersapi.config.DatabaseJpaConfig_;
import com.fpineda.challenge.usersapi.core.command.UpdateUserCommand;
import com.fpineda.challenge.usersapi.core.port.UpdateUserPort;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository.UserEntityMapper;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository.UserRepositoryAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
@Import(DatabaseJpaConfig_.class)
class UpdateUserUseCaseTest {

    @Autowired
    private DatabaseJpaConfig_ databaseConfig;

    @Autowired
    private UserRepositoryAdapter userRepositoryAdapter;    

    private UpdateUserUseCase updateUserUseCase;

    @BeforeEach
    public void setUp() {    
        UpdateUserPort updateUserPort = userRepositoryAdapter;
        updateUserUseCase = new UpdateUserApi(updateUserPort);
    }

    @Test
    void shouldUpdate_User_Successfully() {
        // Prepare data
        var newName = "Jose";
        var entityCreated = databaseConfig.persistUserForTesting();

        var mapper = new UserEntityMapper();
        var user = mapper.toModel(entityCreated);

        var command = UpdateUserCommand.builder().id(user.getId()).email(user.getEmail())
                .address(user.getAddress()).birthDate(user.getBirthDate()).name(newName).build();

        // Execution
        var result = updateUserUseCase.updateUser(command);

        // Assertions
        Assertions.assertEquals(newName, result.getName());

    }

    @Test
    void shouldThrow_EntityNotFoundException() {
        // Prepare data
        var newName = "Jose";
        var invalidId = 100L;
        var entityCreated = databaseConfig.persistUserForTesting();

        var mapper = new UserEntityMapper();
        var user = mapper.toModel(entityCreated);

        var command = UpdateUserCommand.builder().id(invalidId).email(user.getEmail())
                .address(user.getAddress()).birthDate(user.getBirthDate()).name(newName).build();

        // Execution and Assertion
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> updateUserUseCase.updateUser(command));       

    }

}
