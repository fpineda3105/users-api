package com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository;

import com.fpineda.challenge.usersapi.core.model.Address;
import com.fpineda.challenge.usersapi.core.model.User;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.entity.AddressEntity;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.entity.UserEntity;

public class UserEntityMapper implements EntityMapper<User, UserEntity> {

    @Override
    public UserEntity toEntity(final User from) {
        var entity = new UserEntity();
        entity.setName(from.getName());
        entity.setEmail(from.getEmail());
        entity.setBirthDate(from.getBirthDate());

        var fromAddress = from.getAddress();
        var entityAddress = new AddressEntity();
        entityAddress.setCity(fromAddress.getCity());
        entityAddress.setCountry(fromAddress.getCountry());
        entityAddress.setState(fromAddress.getState());
        entityAddress.setZipCode(fromAddress.getZip());
        entityAddress.setStreet(fromAddress.getStreet());

        entity.setAddress(entityAddress);
        return entity;
    }

    @Override
    public User toModel(final UserEntity from) {
        var fromAddress = from.getAddress();
        var address = Address.builder().id(from.getId()).city(fromAddress.getCity())
                .country(fromAddress.getCountry()).state(fromAddress.getState())
                .street(fromAddress.getStreet()).zip(fromAddress.getZipCode()).build();

        return User.builder().id(from.getId()).name(from.getName()).email(from.getEmail())
                .birthDate(from.getBirthDate()).address(address).build();
    }



}
