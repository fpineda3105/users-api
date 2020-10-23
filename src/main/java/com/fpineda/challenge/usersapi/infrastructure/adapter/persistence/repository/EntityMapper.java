package com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository;

public interface EntityMapper <I, O> {

    O toEntity(I from);

    I toModel(O from);
    
}
