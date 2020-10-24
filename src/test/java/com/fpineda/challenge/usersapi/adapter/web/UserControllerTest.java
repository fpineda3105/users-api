package com.fpineda.challenge.usersapi.adapter.web;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import javax.persistence.Id;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpineda.challenge.usersapi.core.command.CreateUserCommand;
import com.fpineda.challenge.usersapi.core.usecase.CreateUserUseCase;
import com.fpineda.challenge.usersapi.core.usecase.DeleteUserByIdUseCase;
import com.fpineda.challenge.usersapi.core.usecase.FetchAllUsersUseCase;
import com.fpineda.challenge.usersapi.core.usecase.FetchUserByIdUseCase;
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
class UserControllerTest {

    private static final String BASE_PATH = "/users";

    @Configuration
    @ComponentScan(basePackages = "com.fpineda.challenge.usersapi.infrastructure.adapter.web")
    static class TestConfiguration {

    }

    @Autowired
    MockMvc mockMvc;

    private static ObjectMapper mapper;

    @MockBean
    private CreateUserUseCase createUserUseCase;

    @MockBean
    private FetchAllUsersUseCase fetchAllUsersUseCase;

    @MockBean
    private FetchUserByIdUseCase fetchUserUseCase;

    @MockBean
    private DeleteUserByIdUseCase deleteUserUseCase;

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void shouldCreateUser_Successfully() throws Exception {
        // Prepare data and mocks
        var createUserDto = TestUtilsFactory.createUserDto();
        var userExpected = TestUtilsFactory.createUser();

        when(createUserUseCase.create(any(CreateUserCommand.class))).thenReturn(userExpected);

        // Execute and Assertions
        mockMvc.perform(post(BASE_PATH).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createUserDto))).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

    }

    @Test
    void shouldReturn_List_With_OneUser() throws Exception {
        // Prepare data
        var userList = TestUtilsFactory.createListUsers();

        when(fetchAllUsersUseCase.fetchAll()).thenReturn(userList);

        // Execution and Assertions
        mockMvc.perform(get(BASE_PATH)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    void shouldReturn_UserById_Sucessfully() throws Exception {
        // Prepare data
        var user = TestUtilsFactory.createUser();

        when(fetchUserUseCase.fetchById(1L)).thenReturn(user);

        // Execution and assertions
        mockMvc.perform(get(BASE_PATH + "/" + 1L)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", comparesEqualTo(user.getName())));
    }

    @Test
    void shouldDelete_UserById_Successfully() throws Exception {
        long id = 1L;
        //Assertions
        mockMvc.perform(delete(BASE_PATH + "/" + id)).andExpect(status().isNoContent());
        
    }


    static String asJsonString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "";
        }
    }


}
