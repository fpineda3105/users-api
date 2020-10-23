package com.fpineda.challenge.usersapi.core.command;

import java.time.LocalDate;
import com.fpineda.challenge.usersapi.core.model.Address;
import com.fpineda.challenge.usersapi.core.model.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateUserCommand {

    private String name;
    private String email;
    private LocalDate birthDate;
    private Address address;

    public User toUser() {
        return User.builder().name(name).email(email).address(address).birthDate(birthDate).build();
    }

}
