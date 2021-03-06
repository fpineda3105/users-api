package com.fpineda.challenge.usersapi.config;

import com.fpineda.challenge.usersapi.api.CreateUserApi;
import com.fpineda.challenge.usersapi.api.DeleteUserApi;
import com.fpineda.challenge.usersapi.api.FetchAllUsersApi;
import com.fpineda.challenge.usersapi.api.FetchUserByIdApi;
import com.fpineda.challenge.usersapi.api.UpdateUserApi;
import com.fpineda.challenge.usersapi.core.usecase.CreateUserUseCase;
import com.fpineda.challenge.usersapi.core.usecase.DeleteUserByIdUseCase;
import com.fpineda.challenge.usersapi.core.usecase.FetchAllUsersUseCase;
import com.fpineda.challenge.usersapi.core.usecase.FetchUserByIdUseCase;
import com.fpineda.challenge.usersapi.core.usecase.UpdateUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import(DatabaseConfig.class)
@ComponentScan(basePackages = "com.fpineda.challenge.usersapi.infrastructure.adapter.web")
public class AppConfig {

    private DatabaseConfig databaseConfig;

    public AppConfig(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Bean
    public CreateUserUseCase createUserUseCase() {
        return new CreateUserApi(databaseConfig.userRepositoryAdapter());
    }

    @Bean
    public FetchAllUsersUseCase fetchAllUsersUseCase() {
        return new FetchAllUsersApi(databaseConfig.userRepositoryAdapter());
    }

    @Bean
    public FetchUserByIdUseCase fetchUserByIdUseCase() {
        return new FetchUserByIdApi(databaseConfig.userRepositoryAdapter());
    }

    @Bean
    public DeleteUserByIdUseCase deleteUserByIdUseCase(){
        return new DeleteUserApi(databaseConfig.userRepositoryAdapter());
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase() {
        return new UpdateUserApi(databaseConfig.userRepositoryAdapter());
    }

    @Bean
    public SwaggerConfig swaggerConfig() {
        return new SwaggerConfig();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fpineda.challenge.usersapi.infrastructure.adapter.web"))
                .build();
    }

}
