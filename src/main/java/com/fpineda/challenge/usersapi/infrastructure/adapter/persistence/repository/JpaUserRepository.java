package com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository;

import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    
}
