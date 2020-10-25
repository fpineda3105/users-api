package com.fpineda.challenge.usersapi.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fpineda.challenge.usersapi.core.command.CreateUserCommand;
import com.fpineda.challenge.usersapi.core.model.Address;
import com.fpineda.challenge.usersapi.core.model.User;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.entity.UserEntity;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository.UserEntityMapper;
import com.fpineda.challenge.usersapi.infrastructure.adapter.web.dto.CreateUserDto;
import com.fpineda.challenge.usersapi.infrastructure.adapter.web.dto.UpdateUserDto;
import com.fpineda.challenge.usersapi.infrastructure.adapter.web.dto.AddressDto;

public class TestUtilsFactory {

    public static User createUser() {
        var address = createAddress();
        return User.builder().id(1L).name("Fernando").birthDate(LocalDate.of(1988, 05, 31))
                .email("fpineda@gmail.com").address(address).build();
    }

    public static Address createAddress() {
        return Address.builder().city("Caracas").state("Dtto. Capital").country("Venezuela")
                .street("Andres Bello").zip("3070").build();
    }

    public static UserEntity createUserEntity() {
        var userEntityMapper = new UserEntityMapper();

        return userEntityMapper.toEntity(createUser());

    }

    public static CreateUserCommand createUserCommand() {
        var user = createUser();
        return CreateUserCommand.builder().address(user.getAddress()).birthDate(user.getBirthDate())
                .email(user.getEmail()).name(user.getName()).build();
    }

    public static CreateUserDto createUserDto() {
        return CreateUserDto.builder().name("Fernando").email("fpineda@gmail.com")
                .birthDate(LocalDate.of(1988, 05, 31)).address(createAddressDto()).build();
    }

    public static AddressDto createAddressDto() {
        var addressDto = new AddressDto();
        addressDto.setCity("Caracas");
        addressDto.setCountry("Venezuela");
        addressDto.setState("Dtto. Capital");
        addressDto.setZip("15378");
        addressDto.setStreet("Andres Bello");
        return addressDto;
    }

    public static List<User> createListUsers() {
        var user = createUser();
        var result = new ArrayList<User>();
        result.add(user);
        return result;
    }

    public static UpdateUserDto createUpdateUserDto() {
        return UpdateUserDto.builder().id(1L).name("Fernando").email("fpineda@gmail.com")
        .birthDate(LocalDate.of(1988, 05, 31)).address(createAddressDto()).build();
    }

}
