package com.fpineda.challenge.usersapi.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(
        basePackages = "com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.entity")
@EnableJpaRepositories(
        basePackages = "com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository")     
@DataJpaTest
public class DatabaseJpaConfig_ {


    // @Bean
    // public UserRepositoryAdapter userRepositoryAdapter() {
    // return new UserRepositoryAdapter(jpaUserRepository);
    // }

}
