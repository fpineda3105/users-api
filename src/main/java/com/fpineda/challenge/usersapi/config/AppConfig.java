package com.fpineda.challenge.usersapi.config;

import com.fpineda.challenge.usersapi.api.CreateUserApi;
import com.fpineda.challenge.usersapi.core.usecase.CreateUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DatabaseConfig.class)
@ComponentScan(
        basePackages = "com.fpineda.challenge.usersapi.infrastructure.adapter.web")
public class AppConfig {

    private DatabaseConfig databaseConfig;

    public AppConfig(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Bean
    public CreateUserUseCase createUserUseCase() {
        return new CreateUserApi(databaseConfig.userRepositoryAdapter());
    }

}
