package com.fpineda.challenge.usersapi.infrastructure.adapter.web.dto;

import com.fpineda.challenge.usersapi.core.model.Address;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@Setter
@NoArgsConstructor
@ToString
public class AddressDto {

    private String street;

    private String state;

    private String city;

    private String country;

    private String zip;

    public Address toAddress() {
        return Address.builder().city(city).country(country).state(state).zip(zip).street(street)
                .build();
    }

}
