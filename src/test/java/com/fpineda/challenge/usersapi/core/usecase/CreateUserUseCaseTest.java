package com.fpineda.challenge.usersapi.core.usecase;

import com.fpineda.challenge.usersapi.api.CreateUserApi;
import com.fpineda.challenge.usersapi.config.DatabaseJpaConfig_;
import com.fpineda.challenge.usersapi.core.port.CreateUserPort;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository.JpaUserRepository;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository.UserRepositoryAdapter;
import com.fpineda.challenge.usersapi.utils.TestUtilsFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})

@ContextConfiguration(classes = {DatabaseJpaConfig_.class})
class CreateUserUseCaseTest {

    private CreateUserUseCase userUseCase;

    @Autowired
    public JpaUserRepository jpaUserRepository;

    @BeforeEach
    public void setUp() {

        CreateUserPort userPort = new UserRepositoryAdapter(jpaUserRepository);         

        userUseCase = new CreateUserApi(userPort);
    }

    @Test
    void should_CreateUser_Successfully() {
        
        var command = TestUtilsFactory.createUserCommand();

        var result = userUseCase.create(command);

        // Assertions
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals(command.getName(), result.getName());

    }
}
