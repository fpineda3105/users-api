package com.fpineda.challenge.usersapi.core.usecase;

import com.fpineda.challenge.usersapi.api.CreateUserApi;
import com.fpineda.challenge.usersapi.config.DatabaseJpaConfig_;
import com.fpineda.challenge.usersapi.core.port.CreateUserPort;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository.UserRepositoryAdapter;
import com.fpineda.challenge.usersapi.utils.TestUtilsFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
@Import(DatabaseJpaConfig_.class)
class CreateUserUseCaseTest {

    @Autowired
    private DatabaseJpaConfig_ databaseConfig;

    @Autowired
    private UserRepositoryAdapter userRepositoryAdapter;

    private CreateUserUseCase userUseCase;

    @BeforeEach
    public void setUp() {

        CreateUserPort userPort = userRepositoryAdapter;         

        userUseCase = new CreateUserApi(userPort);
    }

    @Test
    void should_CreateUser_Successfully() {        
        // Prepare Data
        var command = TestUtilsFactory.createUserCommand();

        // Execution
        var result = userUseCase.create(command);

        // Assertions
        Assertions.assertTrue(result.getId() > 0);
        Assertions.assertEquals(command.getName(), result.getName());

    }

    @AfterEach
    public void DeleteAllUsers() {
        databaseConfig.tearDown();
    }
}
