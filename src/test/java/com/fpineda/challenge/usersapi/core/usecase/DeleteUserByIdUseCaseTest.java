package com.fpineda.challenge.usersapi.core.usecase;

import javax.persistence.EntityNotFoundException;
import com.fpineda.challenge.usersapi.api.DeleteUserApi;
import com.fpineda.challenge.usersapi.api.FetchUserByIdApi;
import com.fpineda.challenge.usersapi.config.DatabaseJpaConfig_;
import com.fpineda.challenge.usersapi.core.port.DeleteUserByIdPort;
import com.fpineda.challenge.usersapi.core.port.FetchUserByIdPort;
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
class DeleteUserByIdUseCaseTest {

    @Autowired
    private DatabaseJpaConfig_ databaseConfig;

    @Autowired
    private UserRepositoryAdapter userRepositoryAdapter;

    private DeleteUserByIdUseCase deleteUserUseCase;

    private FetchUserByIdUseCase fetchUserUseCase;

    @BeforeEach
    public void setUp() {

        FetchUserByIdPort fetchUserPort = userRepositoryAdapter;
        fetchUserUseCase = new FetchUserByIdApi(fetchUserPort);

        DeleteUserByIdPort deleteUserPort = userRepositoryAdapter;
        deleteUserUseCase = new DeleteUserApi(deleteUserPort);
    }

    @Test
    void shouldDelete_User_Successfully() {
        // Prepare data
        var entityCreated = databaseConfig.persistUserForTesting();
        var id = entityCreated.getId();

        // Execute
        deleteUserUseCase.deleteById(id);

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> fetchUserUseCase.fetchById(id));
    }

    @Test
    void shouldThrow_Exception_NotFound() {
        // Prepare data

        // Execution & Assertion
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> deleteUserUseCase.deleteById(100L));
    }

}
