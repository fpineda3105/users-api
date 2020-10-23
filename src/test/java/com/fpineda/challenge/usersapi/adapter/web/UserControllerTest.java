package com.fpineda.challenge.usersapi.adapter.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpineda.challenge.usersapi.core.command.CreateUserCommand;
import com.fpineda.challenge.usersapi.core.usecase.CreateUserUseCase;
import com.fpineda.challenge.usersapi.infrastructure.adapter.web.controller.UserController;
import com.fpineda.challenge.usersapi.utils.TestUtilsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = UserController.class)
public class UserControllerTest {

    @Configuration
    @ComponentScan(
        basePackages = "com.fpineda.challenge.usersapi.infrastructure.adapter.web")
    static class TestConfiguration {

    }
    
    @Autowired
    MockMvc mockMvc;

    private static ObjectMapper mapper;

    @MockBean
    private CreateUserUseCase createUserUseCase;

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void should_CreateUser_Successfully() throws Exception {
        // Prepare data and mocks
        var createUserDto = TestUtilsFactory.createUserDto();
        var userExpected = TestUtilsFactory.createUser();

        when(createUserUseCase.create(any(CreateUserCommand.class))).thenReturn(userExpected);

        // Execute and Assertions
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(asJsonString(createUserDto)))
        .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1));
        

    }

    
    static String asJsonString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    
}
