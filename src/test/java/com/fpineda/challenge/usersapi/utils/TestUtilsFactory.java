package com.fpineda.challenge.usersapi.utils;

import java.time.LocalDate;
import com.fpineda.challenge.usersapi.core.command.CreateUserCommand;
import com.fpineda.challenge.usersapi.core.model.Address;
import com.fpineda.challenge.usersapi.core.model.User;
import com.fpineda.challenge.usersapi.infrastructure.adapter.web.dto.CreateUserDto;
import com.fpineda.challenge.usersapi.infrastructure.adapter.web.dto.CreateUserDto.AddressDto;

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

}
