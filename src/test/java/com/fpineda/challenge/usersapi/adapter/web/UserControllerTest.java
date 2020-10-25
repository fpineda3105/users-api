package com.fpineda.challenge.usersapi.adapter.web;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import javax.persistence.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpineda.challenge.usersapi.core.command.CreateUserCommand;
import com.fpineda.challenge.usersapi.core.command.UpdateUserCommand;
import com.fpineda.challenge.usersapi.core.usecase.CreateUserUseCase;
import com.fpineda.challenge.usersapi.core.usecase.DeleteUserByIdUseCase;
import com.fpineda.challenge.usersapi.core.usecase.FetchAllUsersUseCase;
import com.fpineda.challenge.usersapi.core.usecase.FetchUserByIdUseCase;
import com.fpineda.challenge.usersapi.core.usecase.UpdateUserUseCase;
import com.fpineda.challenge.usersapi.infrastructure.adapter.web.controller.UserController;
import com.fpineda.challenge.usersapi.utils.TestUtilsFactory;
import org.junit.jupiter.api.Assertions;
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

    @MockBean
    private UpdateUserUseCase updateUserUseCase;

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void shouldCreateUser_Successfully() throws Exception {
        // Prepare data and mocks
        var createUserDto = TestUtilsFactory.createUserDto();
        var userExpected = TestUtilsFactory.createUser();
        var locationExpected = "/users/" + userExpected.getId();

        when(createUserUseCase.create(any(CreateUserCommand.class))).thenReturn(userExpected);

        // Execute and Assertions
        var mvcResult = mockMvc.perform(post(BASE_PATH).contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(createUserDto))).andReturn();

        var headerLocation = mvcResult.getResponse().getHeader("Location");
        Assertions.assertTrue(headerLocation.contains(locationExpected));
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
    void shouldReturn_StatusNotFound() throws Exception {
        // Prepare Data
        when(fetchUserUseCase.fetchById(anyLong())).thenThrow(new EntityNotFoundException());

        //Execution and Assertion
        mockMvc.perform(get(BASE_PATH + "/" + 1L)).andExpect(status().isNotFound());
    }

    @Test
    void shouldDelete_UserById_Successfully() throws Exception {
        long id = 1L;
        //Assertions
        mockMvc.perform(delete(BASE_PATH + "/" + id)).andExpect(status().isNoContent());
        
    }

    @Test
    void shouldReturn_StatusBadRequest_CreatingUserByName() throws Exception {
        // Prepare data and mocks
        var createUserDto = TestUtilsFactory.createUserDto();
                
        createUserDto.setName("234234");

        // Execute and Assertions
        mockMvc.perform(post(BASE_PATH).contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(createUserDto))).andExpect(status().isBadRequest());    
    }
    
    @Test
    void shouldReturn_StatusBadRequest_CreatingUserByEmail() throws Exception {
        // Prepare data and mocks
        var createUserDto = TestUtilsFactory.createUserDto();
                
        createUserDto.setEmail("adkjgsakj214");

        // Execute and Assertions
        mockMvc.perform(post(BASE_PATH).contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(createUserDto))).andExpect(status().isBadRequest());    
    }

    @Test
    void shouldUpdateUser_Successfully() throws Exception {
        // Prepare data and mocks
        var newName = "Fernando updated";
        var updateUserDto = TestUtilsFactory.createUpdateUserDto();
        updateUserDto.setName(newName);
        
        var userExpected = TestUtilsFactory.createUser();
        userExpected.setName(newName);

        when(updateUserUseCase.updateUser(any(UpdateUserCommand.class))).thenReturn(userExpected);

        // Execute and Assertions
        mockMvc.perform(put("/users/" + 1L).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updateUserDto))).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", comparesEqualTo(userExpected.getName())));
    }


    static String asJsonString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "";
        }
    }


}
