package com.fpineda.challenge.usersapi.core.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class User {

    private long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private Address address;

}
