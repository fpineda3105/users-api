package com.fpineda.challenge.usersapi.config;

import javax.persistence.EntityManagerFactory;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository.JpaUserRepository;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository.UserRepositoryAdapter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EntityScan(
    basePackages = "com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.entity")
@EnableJpaRepositories(
    basePackages = "com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository")
public class DatabaseConfig {

  private ApplicationContext applicationContext;

  public DatabaseConfig(ApplicationContext applicationContext) {
      this.applicationContext = applicationContext;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory);
    return txManager;
  }

  @Bean
  public UserRepositoryAdapter userRepositoryAdapter() {
    JpaUserRepository jpaUserRepository = applicationContext.getBean(JpaUserRepository.class);
    return new UserRepositoryAdapter(jpaUserRepository);
  }

}
