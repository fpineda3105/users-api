package com.fpineda.challenge.usersapi.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Address {

    private long id;
    private String street;
    private String state;
    private String city;
    private String country;
    private String zip;

}
