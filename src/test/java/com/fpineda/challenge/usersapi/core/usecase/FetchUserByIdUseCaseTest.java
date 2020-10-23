package com.fpineda.challenge.usersapi.core.usecase;

import javax.persistence.EntityNotFoundException;
import com.fpineda.challenge.usersapi.api.FetchUserByIdApi;
import com.fpineda.challenge.usersapi.config.DatabaseJpaConfig_;
import com.fpineda.challenge.usersapi.core.port.FetchUserByIdPort;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository.UserRepositoryAdapter;
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
class FetchUserByIdUseCaseTest {

    @Autowired
    private DatabaseJpaConfig_ databaseConfig;
    
    @Autowired
    private UserRepositoryAdapter userRepositoryAdapter;

    private FetchUserByIdUseCase fetchUserUseCase;

    @BeforeEach
    public void setUp() {

        FetchUserByIdPort userPort = userRepositoryAdapter;         

        fetchUserUseCase = new FetchUserByIdApi(userPort);
    }

    @Test
    void shouldReturn_UserById_Succesfully(){
        // Prepare data
        var entity = databaseConfig.persistUserForTesting();

        // Execution
        var result = fetchUserUseCase.fetchById(entity.getId());

        // Assertions
        Assertions.assertEquals(entity.getName(), result.getName());

    }

    @Test
    void should_Throw_EntityNotFoundException(){                
        // Execution && Assertions
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            fetchUserUseCase.fetchById(1L);
        });

    }

    @AfterEach
    public void DeleteAllUsers() {
        databaseConfig.tearDown();
    }

    
}
