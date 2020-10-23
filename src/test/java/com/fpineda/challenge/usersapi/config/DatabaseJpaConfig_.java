package com.fpineda.challenge.usersapi.config;

import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.entity.UserEntity;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository.JpaUserRepository;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository.UserRepositoryAdapter;
import com.fpineda.challenge.usersapi.utils.TestUtilsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.entity")
@EnableJpaRepositories(
                basePackages = "com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository")
@DataJpaTest
public class DatabaseJpaConfig_ {

        @Autowired
        public JpaUserRepository jpaUserRepository;

        @Bean
        public UserRepositoryAdapter userRepositoryAdapter() {
                return new UserRepositoryAdapter(jpaUserRepository);
        }
        
        public void tearDown(){
                jpaUserRepository.deleteAll();
        }

        public UserEntity persistUserForTesting() {
                return jpaUserRepository.save(TestUtilsFactory.createUserEntity());
        }


}
