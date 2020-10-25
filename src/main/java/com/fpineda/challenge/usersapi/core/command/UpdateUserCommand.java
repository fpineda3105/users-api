package com.fpineda.challenge.usersapi.core.command;

import java.time.LocalDate;
import com.fpineda.challenge.usersapi.core.model.Address;
import com.fpineda.challenge.usersapi.core.model.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateUserCommand {

    private long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private Address address;

    public User toUser() {
        return User.builder().id(id).name(name).email(email).address(address).birthDate(birthDate)
                .build();
    }
}
