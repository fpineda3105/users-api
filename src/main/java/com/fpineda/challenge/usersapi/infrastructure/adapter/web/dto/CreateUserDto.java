package com.fpineda.challenge.usersapi.infrastructure.adapter.web.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fpineda.challenge.usersapi.core.command.CreateUserCommand;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class CreateUserDto {

    private String name;

    private String email;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;

    private AddressDto address;

    public CreateUserCommand toCommand() {
        return CreateUserCommand.builder().name(name).email(email).birthDate(birthDate)
                .address(address.toAddress()).build();
    }

}
