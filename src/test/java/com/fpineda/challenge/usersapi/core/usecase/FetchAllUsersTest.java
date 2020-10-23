package com.fpineda.challenge.usersapi.core.usecase;

import com.fpineda.challenge.usersapi.api.FetchAllUsersApi;
import com.fpineda.challenge.usersapi.config.DatabaseJpaConfig_;
import com.fpineda.challenge.usersapi.core.port.FetchAllUserPort;
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
class FetchAllUsersTest {
   
    @Autowired
    private DatabaseJpaConfig_ databaseConfig;
    
    @Autowired
    private UserRepositoryAdapter userRepositoryAdapter;

    private FetchAllUsersUseCase allUsersUseCase;

    @BeforeEach
    public void setUp() {

        FetchAllUserPort userPort = userRepositoryAdapter;         

        allUsersUseCase = new FetchAllUsersApi(userPort);
    }

    @Test
    void shouldNot_Fetch_Users() {
        // Execution        
        var result = allUsersUseCase.fetchAll();

        // Assertion
        Assertions.assertEquals(0, result.size());
    }

    @Test
    void should_Fetch_Users_With_Size_1(){
        // Prepare data
        databaseConfig.insertUser();

        // Execution        
        var result = allUsersUseCase.fetchAll();

        // Assertion
        Assertions.assertEquals(1, result.size());

    }
    

    @AfterEach
    public void DeleteAllUsers() {
        databaseConfig.tearDown();
    }
}
