package com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository;

import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAddressPersistenceAdapter extends JpaRepository<AddressEntity, Long> {
    
}
